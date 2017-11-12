package controller;

import entity.Account;
import io.ebean.Ebean;
import oauth.OauthAccount;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import tmp.DataSingleton;

/**
 * @author sheol on 9/26/17 at 4:47 PM
 * @project SpringRestStarter
 */
@RestController
@RequestMapping("/account")
@SessionAttributes("oauth_account")
public class AccountController {
    private static final int NONE = 0;
    private static final int USERNAME = 1;
    private static final int EMAIL = 2;

    private String myResponse(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", msg);
        return jsonObject.toString();
    }

    private String myError(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", msg);
        return jsonObject.toString();
    }

    @GetMapping
    public String getAccounts() {
        return DataSingleton.gson().toJson(Ebean.createQuery(Account.class).findList());
    }

    private int accountValidator(Account account) {
        Account currentAccount = Ebean.createQuery(Account.class).where()
                .or().eq("username", account.getUsername())
                .or().eq("email", account.getEmail()).findOne();
        if (currentAccount != null) {
            if (currentAccount.getUsername().equals(account.getUsername())) {
                return USERNAME;
            }
            if (currentAccount.getEmail().equals(account.getEmail())) {
                return EMAIL;
            }
        }
        return NONE;
    }

    private String accountError(Account account) {
        int error = accountValidator(account);
        if (error == USERNAME) {
            return myError("username already taken");
        }
        if (error == EMAIL) {
            return myError("email already taken");
        }
        return "";
    }

    @PostMapping
    public String postAccount(@RequestBody Account account) {
        String accountError = accountError(account);
        if (!accountError.equals("")) {
            return accountError;
        }
        //accounts.add(account);
        Ebean.save(account);
        return myResponse("post " + account.getUsername());
    }

    @PutMapping("/{username}")
    public String putAccount(@PathVariable String username, @RequestBody Account account) {
        String accountError = accountError(account);
        if (!accountError.equals("")) {
            return accountError;
        }
        Account currentAccount = Ebean.createQuery(Account.class)
                .where().eq("username", username).findOne();
        if (currentAccount != null) {
            account.setId(currentAccount.getId());
            Ebean.update(account);
            return myResponse("put " + username);
        }
        /*for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(username)) {
                //accounts.set(i, account);
                Ebean.update(account);
                return myResponse("put " + username);
            }
        }*/
        return myError("username not found");
    }

    @DeleteMapping("/{username}")
    public String deleteAccount(@ModelAttribute("oauth_account") OauthAccount oauthAccount,
                                @PathVariable String username) {
        if (!oauthAccount.getAccountUsername().equals(username)) {
            Account account = Ebean.createQuery(Account.class).where()
                    .eq("username", username).findOne();
            if (account != null) {
                Ebean.delete(account);
                return myResponse("delete " + username);
            }
            return myError("username not found");
        }
        return myError("is you !");
    }

    @DeleteMapping
    public String deleteAllAccounts() {
        //accounts.clear();
        Ebean.deleteAll(Ebean.createQuery(Account.class).findList());
        return myResponse("delete all");
    }
}
