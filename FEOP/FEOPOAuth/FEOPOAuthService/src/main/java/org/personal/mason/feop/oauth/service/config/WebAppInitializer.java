package org.personal.mason.feop.oauth.service.config;

import org.personal.mason.feop.oauth.common.config.CommonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

public class WebAppInitializer implements WebApplicationInitializer {

    private static final Logger logger = LoggerFactory.getLogger(WebAppInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(RootConfig.class, CommonConfig.class);
        ctx.refresh();

        servletContext.addListener(new ContextLoaderListener(ctx));
        servletContext.setInitParameter("defaultHtmlEscape", "true");

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebMvcConfig.class);

        FilterRegistration.Dynamic registration = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
        registration.addMappingForUrlPatterns(null, true, "/*");

        registration = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        registration.setInitParameter("encoding", "UTF-8");
        registration.setInitParameter("forceEncoding", "true");
        registration.addMappingForUrlPatterns(null, true, "/*");

        ServletRegistration.Dynamic appServlet = servletContext.addServlet("contact", new DispatcherServlet(context));
        appServlet.setLoadOnStartup(1);
        Set<String> mappingConflicts = appServlet.addMapping("/");
        if (!mappingConflicts.isEmpty()) {
            for (String s : mappingConflicts) {
                logger.error(String.format("Mapping conflict: %s", s));
            }
            throw new IllegalStateException("'appServlet' cannot be mapped to '/'");
        }
    }

}
