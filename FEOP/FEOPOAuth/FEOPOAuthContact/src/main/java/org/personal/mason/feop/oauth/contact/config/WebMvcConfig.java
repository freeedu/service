package org.personal.mason.feop.oauth.contact.config;

import org.personal.mason.feop.oauth.common.client.*;
import org.personal.mason.feop.oauth.common.client.oauth.code.AuthorizationCodeLoginProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;


/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 11/23/13
 * Time: 12:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.personal.mason.feop.oauth.contact.mvc"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
    private static final String MESSAGE_SOURCE = "/WEB-INF/classes/messages";

    @Bean
    public ViewResolver viewResolver() {
        logger.debug("setting up view resolver");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(name = "messageSource")
    public MessageSource configureMessageSource() {
        logger.debug("setting up message source");
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_SOURCE);
        messageSource.setCacheSeconds(10);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.debug("setting up resource handlers");
        registry.addResourceHandler("/resources/").addResourceLocations("/resources/**");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        logger.debug("configureDefaultServletHandling");
        configurer.enable();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
        OAuthAuthenticationInterceptor interceptor = new OAuthAuthenticationInterceptor();
        interceptor.setFoepAuthenticationProcessor(foepAuthenticationProcessor());
        interceptor.setFoepLoginProcessor(foepLoginProcessor());
        registry.addInterceptor(interceptor);
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties maps = new Properties();
        maps.put("org.springframework.web.servlet.PageNotFound", "p404");
        maps.put("org.springframework.dao.DataAccessException", "dataAccessFailure");
        maps.put("org.springframework.transaction.TransactionException", "dataAccessFailure");
        resolver.setExceptionMappings(maps);
        return resolver;
    }

    @Bean
    public FOEPAuthenticationProcessor foepAuthenticationProcessor(){
        AuthorityInterceptor resourceInterceptor = new AuthorityInterceptor("/resources/.*", false);
        AuthorityInterceptor icoInterceptor = new AuthorityInterceptor("/.*.ico", false);
        AuthorityInterceptor contactInterceptor = new AuthorityInterceptor("/contact.*", true);
        contactInterceptor.setAccess("ROLE_USER, ROLE_ADMIN, ROLE_DEV");

        DefaultAuthenticationProcessor processor = new DefaultAuthenticationProcessor(Arrays.asList(resourceInterceptor, icoInterceptor, contactInterceptor));
        processor.setTokenUtils(tokenUtils());
        return processor;
    }

    @Bean
    public TokenUtils tokenUtils() {
        return new DefaultTokenUtils();
    }

    @Bean
    public FOEPLoginProcessor foepLoginProcessor(){
        AuthorizationCodeLoginProcessor processor = new AuthorizationCodeLoginProcessor(clientConfiguration());
        processor.setTokenUtils(tokenUtils());
        return processor;
    }

    @Bean
    public ClientConfiguration clientConfiguration() {
        return new DBClientConfiguration();
    }

}
