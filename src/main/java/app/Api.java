package app;

import entity.Account;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import tmp.DataSingleton;

/**
 * @author sheol on 11/2/17 at 11:04 PM
 * @project SpringRestDemo
 */
@ComponentScan(basePackages = {"controller", "oauth"})
@Import(DelegatingWebMvcConfiguration.class)
public class Api {
    public Api() {
        DataSingleton.getInstance().getAccounts()
                .add(new Account("test", "test", "test@test.fr"));
        ServerConfig config = new ServerConfig();
        config.setName("db");
        config.loadFromProperties();
        EbeanServer server = EbeanServerFactory.create(config);
        //MongoSingleton.getInstance().config("localhost", 27017);
        //MongoSingleton.getInstance().setMongoDatabase("myDatabase");
    }
}
