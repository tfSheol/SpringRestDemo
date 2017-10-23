import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author sheol on 9/26/17 at 4:47 PM
 * @project SpringRestStarter
 */
@EnableAutoConfiguration(exclude = {EmbeddedMongoAutoConfiguration.class,
        MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan(basePackages = {"configuration", "controller"})
public class Main {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
        MongoSingleton.getInstance().config("localhost", 27017);
        MongoSingleton.getInstance().setMongoDatabase("myDatabase");
    }
}
