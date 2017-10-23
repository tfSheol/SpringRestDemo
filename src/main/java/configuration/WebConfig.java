package configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
}
