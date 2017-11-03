package app;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

/**
 * @author sheol on 11/2/17 at 11:04 PM
 * @project SpringRestDemo
 */
@Configuration
@Import(DelegatingWebMvcConfiguration.class)
public class Web {
    
}
