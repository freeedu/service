package org.personal.mason.feop.oauth.service.config.oauth;

import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/6/13
 * Time: 12:51 AM
 * To change this template use File | Settings | File Templates.
 */
public final class OAuth2ServerConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
    private AccessDeniedHandler accessDeniedHandler = new OAuth2AccessDeniedHandler();
    private ClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter;
    private OAuth2AuthenticationProcessingFilter resourcesServerFilter;
    private SecurityExpressionHandler<FilterInvocation> expressionHandler = new OAuth2WebSecurityExpressionHandler();
    private AuthenticationManager authenticationManager;

    public OAuth2ServerConfigurer(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void init(HttpSecurity builder) throws Exception {
        if (builder.getConfigurer(HttpBasicConfigurer.class) == null) {
            builder.httpBasic();
        }
        builder.setSharedObject(AuthenticationEntryPoint.class, authenticationEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.getConfigurer(ExpressionUrlAuthorizationConfigurer.class).getRegistry().expressionHandler(expressionHandler);

        clientCredentialsTokenEndpointFilter = new ClientCredentialsTokenEndpointFilter();
        clientCredentialsTokenEndpointFilter.setAuthenticationManager(authenticationManager);
        clientCredentialsTokenEndpointFilter = postProcess(clientCredentialsTokenEndpointFilter);

        resourcesServerFilter = new OAuth2AuthenticationProcessingFilter();
        resourcesServerFilter.setAuthenticationManager(authenticationManager);
        resourcesServerFilter = postProcess(resourcesServerFilter);

        http.getConfigurer(ExceptionHandlingConfigurer.class).accessDeniedHandler(accessDeniedHandler);

        http.antMatcher("/oauth/token").addFilterBefore(resourcesServerFilter, AbstractPreAuthenticatedProcessingFilter.class)
                .addFilterBefore(clientCredentialsTokenEndpointFilter, BasicAuthenticationFilter.class);
    }
}
