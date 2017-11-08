package tmp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Account;
import entity.Token;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author sheol on 10/30/17 at 4:06 PM
 * @project SpringRestDemo
 */
public class DataSingleton {
    private static DataSingleton instance = new DataSingleton();
    //private List<Account> accounts = new CopyOnWriteArrayList<>();
    //private List<Token> tokens = new CopyOnWriteArrayList<>();

    private DataSingleton() {
    }

    public static DataSingleton getInstance() {
        return instance;
    }

    /*public List<Account> getAccounts() {
        return accounts;
    }*/

    /*public List<Token> getTokens() {
        return tokens;
    }*/

    public static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        return gsonBuilder.create();
    }
}
