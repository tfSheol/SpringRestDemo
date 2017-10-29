package oauth;

import java.util.ArrayList;

/**
 * @author sheol on 10/28/17 at 9:51 PM
 * @project SpringRestDemo
 */
public interface OauthPath {
    void protectResource(ArrayList<String> path);

    void setOauthPath(ArrayList<String> loginPath);

    void excludeResource(ArrayList<String> resource);
}
