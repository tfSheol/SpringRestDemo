package tmp;

import entity.Account;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author sheol on 10/30/17 at 4:06 PM
 * @project SpringRestDemo
 */
public class AccountSingleton {
    private static AccountSingleton instance = new AccountSingleton();
    private List<Account> accounts = new CopyOnWriteArrayList<>();

    private AccountSingleton() {

    }

    public static AccountSingleton getInstance() {
        return instance;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
