package org.personal.mason.feop.oauth.service.config.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpointHandlerMapping;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 11/28/13
 * Time: 7:39 PM
 * To change this template use File | Settings | File Templates.
 */

@Configuration
public abstract class Oauth2ConfigAdapter extends WebSecurityConfigurerAdapter {

    @Bean
    public Oauth2ServerConfigurer oauth2ServerConfigurer() throws Exception {
        return getHttp().getConfigurer(Oauth2ServerConfigurer.class);
    }

    @Bean
    public AuthorizationEndpoint authorizationEndpoint() throws Exception {
        AuthorizationEndpoint authorizationEndpoint = new AuthorizationEndpoint();
        authorizationEndpoint.setTokenGranter(tokenGranter());
        authorizationEndpoint.setClientDetailsService(clientDetails());
        authorizationEndpoint.setAuthorizationCodeServices(authorizationCodeServices());
        return authorizationEndpoint;
    }

    @Bean
    public ConsumerTokenServices consumerTokenServices() throws Exception {
        return oauth2ServerConfigurer().getConsumerTokenServices();
    }

    @Bean
    public TokenEndpoint tokenEndpoint() throws Exception {
        TokenEndpoint tokenEndpoint = new TokenEndpoint();
        tokenEndpoint.setClientDetailsService(clientDetails());
        tokenEndpoint.setTokenGranter(tokenGranter());
        ;
        return tokenEndpoint;
    }

    @Bean
    public AuthorizationCodeTokenGranter authorizationCodeTokenGranter() throws Exception {
        return new AuthorizationCodeTokenGranter(tokenServices(), authorizationCodeServices(), clientDetails());
    }

    @Bean
    public WhitelabelApprovalEndpoint approvalEndpoint() {
        return new WhitelabelApprovalEndpoint();
    }

    @Bean
    public FrameworkEndpointHandlerMapping endpointHandlerMapping() {
        return new FrameworkEndpointHandlerMapping();
    }

    @Bean
    public ClientDetailsService clientDetailsService() throws Exception {
        return clientDetails();
    }

    private AuthorizationServerTokenServices tokenServices() throws Exception {
        return oauth2ServerConfigurer().getTokenServices();
    }

    private AuthorizationCodeServices authorizationCodeServices() throws Exception {
        return oauth2ServerConfigurer().getAuthorizationCodeServices();
    }

    private ClientDetailsService clientDetails() throws Exception {
        return getHttp().getSharedObject(ClientDetailsService.class);
    }

    private TokenGranter tokenGranter() throws Exception {
        return oauth2ServerConfigurer().getTokenGranter();
    }

}
