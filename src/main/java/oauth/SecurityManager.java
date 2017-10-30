package oauth;

import entity.Account;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tmp.AccountSingleton;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
import java.util.List;

/**
 * @author sheol on 10/28/17 at 7:47 PM
 * @project SpringRestDemo
 */
public class SecurityManager implements HandlerInterceptor, OauthPath {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        Enumeration headerName = request.getHeaderNames();
        for (; headerName.hasMoreElements(); ) {
            String header = (String) headerName.nextElement();
            System.err.println(header + " : " + request.getHeader(header));
        }
        String base64Tmp = request.getHeader("authorization").split(" ")[1];
        String[] base64DecodeTmp = new String(Base64.getDecoder().decode(base64Tmp)).split(":");
        String username = base64DecodeTmp[0];
        String password = base64DecodeTmp[1];
        System.err.println(username);
        System.err.println(password);
        List<Account> accounts = AccountSingleton.getInstance().getAccounts();
        for (Account account : accounts) {
            if (account.getUsername().equals(username)
                    && account.getPassword().equals(password)) {
                return true;
            }
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        //response.sendRedirect("/401");
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public void protectResource(ArrayList<String> path) {
        path.add("/account/**");
    }

    @Override
    public void setOauthPath(ArrayList<String> loginPath) {
        loginPath.add("/auth");
    }

    @Override
    public void excludeResource(ArrayList<String> resource) {

    }
}
