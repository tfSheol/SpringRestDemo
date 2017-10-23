package entity;

/**
 * @author sheol on 9/26/17 at 4:47 PM
 * @project SpringRestStarter
 */
public class Account {
    private String username;
    private String password;
    private String email;

    public Account() {
        
    }

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
