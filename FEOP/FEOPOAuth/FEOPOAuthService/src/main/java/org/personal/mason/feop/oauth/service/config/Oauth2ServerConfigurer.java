package org.personal.mason.feop.oauth.service.config;

import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 11/28/13
 * Time: 7:51 PM
 * To change this template use File | Settings | File Templates.
 */

public final class Oauth2ServerConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
    private AccessDeniedHandler accessDeniedHandler = new OAuth2AccessDeniedHandler();

    private ClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter;
    private OAuth2AuthenticationProcessingFilter resoucesServerFilter;
    private AuthorizationServerTokenServices tokenServices;
    private AuthorizationCodeServices authorizationCodeServices;
    private ResourceServerTokenServices resourceTokenServices;
    private TokenStore tokenStore;
    private TokenGranter tokenGranter;
    private ConsumerTokenServices consumerTokenServices;
    private String resourceId = "oauth2-resource";
    private SecurityExpressionHandler<FilterInvocation> expressionHandler = new OAuth2WebSecurityExpressionHandler();

    @Override
    public void init(HttpSecurity builder) throws Exception {
        if(builder.getConfigurers(HttpBasicConfigurer.class) == null){
            builder.httpBasic();
        }
        builder.setSharedObject(AuthenticationEntryPoint.class, authenticationEntryPoint);
    }

    public Oauth2ServerConfigurer resourceId(String resourceId){
        this.resourceId = resourceId;
        return this;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        builder.getConfigurer(ExpressionUrlAuthorizationConfigurer.class).getRegistry().expressionHandler(expressionHandler);//TO

        clientCredentialsTokenEndpointFilter = new ClientCredentialsTokenEndpointFilter();
        clientCredentialsTokenEndpointFilter.setAuthenticationManager(authenticationManager(builder));
        clientCredentialsTokenEndpointFilter = postProcess(clientCredentialsTokenEndpointFilter);

        AuthenticationManager authenticationManager =  oauthAuthenticationManager(builder);
        resoucesServerFilter = new OAuth2AuthenticationProcessingFilter();
        resoucesServerFilter.setAuthenticationManager(authenticationManager);
        resoucesServerFilter = postProcess(resoucesServerFilter);

        this.tokenGranter = tokenGranter(builder);
        this.consumerTokenServices = consumerTokenServices(builder);

        builder.getConfigurer(ExceptionHandlingConfigurer.class).accessDeniedHandler(accessDeniedHandler);
        builder.addFilterBefore(resoucesServerFilter, AbstractPreAuthenticatedProcessingFilter.class);
        builder.addFilterBefore(clientCredentialsTokenEndpointFilter, BasicAuthenticationFilter.class);
    }

    private AuthenticationManager oauthAuthenticationManager(HttpSecurity builder) {
        OAuth2AuthenticationManager auth2AuthenticationManager = new OAuth2AuthenticationManager();
        auth2AuthenticationManager.setResourceId(resourceId);
        auth2AuthenticationManager.setTokenServices(resourceTokenServices(builder));

        return auth2AuthenticationManager;
    }

    private ResourceServerTokenServices resourceTokenServices(HttpSecurity builder) {
        tokenServices(builder);
        return this.resourceTokenServices;
    }

    private AuthorizationServerTokenServices tokenServices(HttpSecurity builder) {
        if(this.tokenServices != null){
            return this.tokenServices;
        }

        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetails());
        this.tokenServices = tokenServices;
        this.resourceTokenServices = tokenServices;
        return tokenServices;
    }

    private TokenStore tokenStore() {
        if(this.tokenStore == null){
            this.tokenStore = new InMemoryTokenStore();
        }

        return this.tokenStore;
    }

    private ClientDetailsService clientDetails(){
        return getBuilder().getSharedObject(ClientDetailsService.class);
    }

    public TokenGranter getTokenGranter() {
        return this.tokenGranter;
    }

    public AuthorizationCodeServices getAuthorizationCodeServices() {
        return authorizationCodeServices;
    }

    private AuthorizationCodeServices authorizationCodeServices(HttpSecurity builder) {
        if(authorizationCodeServices == null){
            authorizationCodeServices = new InMemoryAuthorizationCodeServices();
        }
        return authorizationCodeServices;
    }

    private AuthenticationManager authenticationManager(HttpSecurity builder) throws  Exception{
        return builder.getSharedObject(AuthenticationManagerBuilder.class).build();
        //return builder.getAuthenticationManager();
    }

    public ConsumerTokenServices getConsumerTokenServices() {
        return this.consumerTokenServices;
    }

    private ConsumerTokenServices consumerTokenServices(HttpSecurity builder) {
        if(consumerTokenServices == null){
            DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
            defaultTokenServices.setClientDetailsService(clientDetails());
            defaultTokenServices.setTokenStore(tokenStore());
            this.consumerTokenServices = defaultTokenServices;
        }
        return this.consumerTokenServices;
    }

    private TokenGranter tokenGranter(HttpSecurity builder) throws Exception{
        if(this.tokenGranter == null){
            ClientDetailsService clientDetailsService = clientDetails();
            AuthorizationServerTokenServices tokenServices = tokenServices(builder);
            AuthorizationCodeServices authorizationCodeServices = authorizationCodeServices(builder);
            AuthenticationManager authenticationManager = authenticationManager(builder);

            List<TokenGranter> tokenGranters = new ArrayList<>();
            tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetailsService));
            tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetailsService));
            tokenGranters.add(new ImplicitTokenGranter(tokenServices, clientDetailsService));
            tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetailsService));
            tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, clientDetailsService));
            this.tokenGranter = new CompositeTokenGranter(tokenGranters);
        }
        return this.tokenGranter;
    }

    public AuthorizationServerTokenServices getTokenServices() {
        return tokenServices;
    }
}
