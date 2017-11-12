package oauth;

/**
 * @author sheol on 11/12/17 at 4:24 PM
 * @project SpringRestDemo
 */
public class OauthAccount {
    private Long accountId;
    private String accountUsername;
    private String accountToken;
    private String accountEmail;

    public OauthAccount(Long id, String username, String token, String email) {
        this.accountId = id;
        this.accountUsername = username;
        this.accountToken = token;
        this.accountEmail = email;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }
}
