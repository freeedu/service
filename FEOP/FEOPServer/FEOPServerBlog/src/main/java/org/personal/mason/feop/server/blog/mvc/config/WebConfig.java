package org.personal.mason.feop.server.blog.mvc.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "org.personal.mason.feop.server.blog.mvc")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/", "classpath:/META-INF/resources/vendor/")
                .setCachePeriod(31556926);
        super.addResourceHandlers(registry);
    }

}
