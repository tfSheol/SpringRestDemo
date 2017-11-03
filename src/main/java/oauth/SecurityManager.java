package oauth;

import entity.Token;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tmp.DataSingleton;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
            if (header.equals("authorization")) {
                String authorization_tmp[] = request.getHeader("authorization").split(" ");
                if (authorization_tmp.length == 2) {
                    String token_type = authorization_tmp[0];
                    String token_value = authorization_tmp[1];
                    if (token_type.equals("Bearer")) {
                        List<Token> tokens = DataSingleton.getInstance().getTokens();
                        for (Token token : tokens) {
                            if (token.getToken().equals(token_value)) {
                                return true;
                            }
                        }
                    }
                }
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
        loginPath.add("/oauth/**");
    }

    @Override
    public void excludeResource(ArrayList<String> resource) {

    }
}
