package oauth;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * @author sheol on 10/28/17 at 7:47 PM
 * @project SpringRestDemo
 */
public class SecurityManager implements HandlerInterceptor, OauthPath {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //String xHeader = request.getHeader("X-Auth-Token");
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
