package org.personal.mason.feop.oauth.service.config;

import org.personal.mason.feop.oauth.service.common.oauth2.OAuth2UserDetailsServiceImpl;
import org.personal.mason.feop.oauth.service.common.oauth2.extention.FOEPClientDetailsService;
import org.personal.mason.feop.oauth.service.common.oauth2.extention.FOEPTokenStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.TokenServicesUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 12/5/13
 * Time: 1:05 PM
 * To change this template use File | Settings | File Templates.
 */

@Configuration
public class OAuthConfig {

    @Bean
    public ClientDetailsService clientDetailsService(){
        FOEPClientDetailsService clientDetailsService = new FOEPClientDetailsService();
        return clientDetailsService;
    }

    @Bean
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService());
        return tokenServices;
    }

    @Bean
    public TokenStore tokenStore(){
        FOEPTokenStore tokenStore = new FOEPTokenStore();
        return tokenStore;
    }

    @Bean
    public UserApprovalHandler userApprovalHandler(){
        TokenServicesUserApprovalHandler userApprovalHandler = new TokenServicesUserApprovalHandler();
        userApprovalHandler.setTokenServices(tokenServices());
        return userApprovalHandler;
    }

    @Bean
    public OAuth2UserDetailsServiceImpl userDetailsService(){
        OAuth2UserDetailsServiceImpl userDetailsService = new OAuth2UserDetailsServiceImpl();
        return userDetailsService;
    }

    @Bean
    public ClientDetailsUserDetailsService clientDetailsUserService(){
        ClientDetailsUserDetailsService userDetailsService = new ClientDetailsUserDetailsService(clientDetailsService());
        return userDetailsService;
    }

    @Bean
    public OAuth2MethodSecurityExpressionHandler oauthExpressionHandler(){
        return new OAuth2MethodSecurityExpressionHandler();
    }

    @Bean
    public OAuth2WebSecurityExpressionHandler oauthWebExpressionHandler(){
        return new OAuth2WebSecurityExpressionHandler();
    }

    @Bean
    public OAuth2AuthenticationProcessingFilter resourceServerFilter(){
        OAuth2AuthenticationProcessingFilter processingFilter = new OAuth2AuthenticationProcessingFilter();
        //processingFilter.s
        return processingFilter;
    }

}
