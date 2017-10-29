package configuration;

import oauth.SecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sheol on 10/11/17 at 1:10 PM
 * @project SpringRestStarter
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        Map<String, String> parameterMap = new HashMap<String, String>();
        parameterMap.put("charset", "utf-8");
        configurer.defaultContentType(new MediaType(MediaType.APPLICATION_JSON, parameterMap));
    }

    @Bean
    protected SecurityManager getSecurityManager() {
        return new SecurityManager();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        SecurityManager securityManager = getSecurityManager();
        ArrayList<String> protectedResource = new ArrayList<String>();
        ArrayList<String> excludedResource = new ArrayList<String>();
        ArrayList<String> oauthPath = new ArrayList<String>();
        securityManager.protectResource(protectedResource);
        securityManager.excludeResource(excludedResource);
        securityManager.setOauthPath(oauthPath);
        oauthPath.addAll(excludedResource);
        registry.addInterceptor(securityManager)
                .addPathPatterns(protectedResource.toArray(new String[protectedResource.size()]))
                .excludePathPatterns(oauthPath.toArray(new String[oauthPath.size()]));
    }
}
