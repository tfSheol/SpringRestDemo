package oauth;

import com.google.gson.Gson;
import entity.Account;
import entity.Token;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tmp.AccountSingleton;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * @author sheol on 10/29/17 at 1:00 PM
 * @project SpringRestDemo
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {
    @PostMapping("/auth")
    public String auth(@RequestHeader(value = "authorization") String authorization,
                       HttpServletResponse response) {
        String base64Tmp = authorization.split(" ")[1];
        String[] base64DecodeTmp = new String(Base64.getDecoder().decode(base64Tmp)).split(":");
        if (base64DecodeTmp.length == 2) {
            String username = base64DecodeTmp[0];
            String password = base64DecodeTmp[1];
            List<Account> accounts = AccountSingleton.getInstance().getAccounts();
            for (Account account : accounts) {
                if (account.getUsername().equals(username)
                        && account.getPassword().equals(password)) {
                    Token token = new Token();
                    token.setToken(UUID.randomUUID().toString());
                    token.setUsername(account.getUsername());
                    System.err.println("generate :" + token.getToken());
                    AccountSingleton.getInstance().getTokens().add(token);
                    return new Gson().toJson(token);
                }
            }
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return "";
    }

    @PostMapping("/token_auth")
    public String tokenAuth() {
        // TODO: 10/29/17 validate Bearer auth
        // TODO: 10/29/17 GrantType.client_credentials
        // TODO: 10/29/17 GrantType.refresh_token
        // TOKEN_TYPE, ACCESS_TOKEN, EXPIRES_IN
        return "";
    }
}
