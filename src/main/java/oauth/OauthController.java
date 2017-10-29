package oauth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sheol on 10/29/17 at 1:00 PM
 * @project SpringRestDemo
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {
    @PostMapping("/auth")
    public String auth() {
        // TODO: 10/29/17 validate Basic auth 
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
