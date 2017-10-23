package controller;

import com.google.gson.Gson;
import entity.Account;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sheol on 9/26/17 at 4:47 PM
 * @project SpringRestStarter
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    private List<Account> accounts = new ArrayList<Account>();

    public AccountController() {
        /* simulate accounts */
        accounts.add(new Account("test", "test", "test@test.fr"));
    }

    private String getResponse(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", msg);
        return jsonObject.toString();
    }

    @GetMapping
    public String getAccounts() {
        return new Gson().toJson(accounts);
    }

    @PostMapping
    public String postAccount(@RequestBody Account account) {
        for (Account currentAccount : accounts) {
            if (currentAccount.getUsername().equals(account.getUsername())) {
                return getResponse("username already taken");
            }
            if (currentAccount.getEmail().equals(account.getEmail())) {
                return getResponse("email already taken");
            }
        }
        accounts.add(account);
        return getResponse("post " + account.getUsername());
    }

    @PutMapping("/{username}")
    public String putAccount(@PathVariable String username, @RequestBody Account account) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(username)) {
                accounts.set(i, account);
                return getResponse("put " + username);
            }
        }
        return getResponse("username not found");
    }

    @DeleteMapping("/{username}")
    public String deleteAccount(@PathVariable String username) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(username)) {
                accounts.remove(i);
                return getResponse("delete " + username);
            }
        }
        return getResponse("username not found");
    }

    @DeleteMapping
    public String deleteAllAccounts() {
        accounts.clear();
        return getResponse("delete all");
    }
}
