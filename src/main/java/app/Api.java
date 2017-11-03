package app;

import entity.Account;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import tmp.DataSingleton;

/**
 * @author sheol on 11/2/17 at 11:04 PM
 * @project SpringRestDemo
 */
@Configuration
@ComponentScan(basePackages = {"controller", "oauth", "configuration"})
@Import(DelegatingWebMvcConfiguration.class)
public class Api {
    public Api() {
        DataSingleton.getInstance().getAccounts()
                .add(new Account("test", "test", "test@test.fr"));
        //MongoSingleton.getInstance().config("localhost", 27017);
        //MongoSingleton.getInstance().setMongoDatabase("myDatabase");
    }
}
