package org.personal.mason.feop.oauth.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 11/28/13
 * Time: 11:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private SecurityConfig securityConfig;

    @Bean
    public OAuth2MethodSecurityExpressionHandler oauthExpressionHandler(){
        return new OAuth2MethodSecurityExpressionHandler();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return securityConfig.authenticationManagerBean();
    }
}
