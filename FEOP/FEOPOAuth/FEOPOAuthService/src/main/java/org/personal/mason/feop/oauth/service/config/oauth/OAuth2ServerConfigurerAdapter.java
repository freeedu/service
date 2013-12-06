package org.personal.mason.feop.oauth.service.config.oauth;

import org.personal.mason.feop.oauth.service.config.OAuthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpointHandlerMapping;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/6/13
 * Time: 1:09 AM
 * To change this template use File | Settings | File Templates.
 */

@Configuration
@Import(OAuthConfig.class)
public abstract class OAuth2ServerConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private TokenGranter tokenGranter;

    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private DefaultTokenServices tokenServices;
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;


    @Bean
    public AuthorizationEndpoint authorizationEndpoint() throws Exception {
        AuthorizationEndpoint authorizationEndpoint = new AuthorizationEndpoint();
        authorizationEndpoint.setTokenGranter(tokenGranters());
        authorizationEndpoint.setClientDetailsService(clientDetailsService);
        authorizationEndpoint.setAuthorizationCodeServices(authorizationCodeServices);
        return authorizationEndpoint;
    }

    @Bean
    public ConsumerTokenServices consumerTokenServices() throws Exception {
        return tokenServices;
    }

    /**
     * @return
     * @throws Exception
     */
    protected AuthorizationServerTokenServices tokenServices() throws Exception {
        return tokenServices;
    }


    @Bean
    public TokenEndpoint tokenEndpoint() throws Exception {
        TokenEndpoint tokenEndpoint = new TokenEndpoint();
        tokenEndpoint.setClientDetailsService(clientDetailsService);
        tokenEndpoint.setTokenGranter(tokenGranters());
        return tokenEndpoint;
    }

    @Bean
    public AuthorizationCodeTokenGranter authorizationTokenGranter() throws Exception {
        return new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetailsService);
    }

    @Bean
    public WhitelabelApprovalEndpoint approvalEndpoint() {
        return new WhitelabelApprovalEndpoint();
    }

    @Bean
    public FrameworkEndpointHandlerMapping endpointHandlerMapping() {
        return new FrameworkEndpointHandlerMapping();
    }


    private TokenGranter tokenGranters() throws Exception {
        if (tokenGranter == null) {
            tokenGranter = new CompositeTokenGranter(Arrays.<TokenGranter>asList(
                    new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetailsService),
                    new ImplicitTokenGranter(tokenServices, clientDetailsService),
                    new ClientCredentialsTokenGranter(tokenServices, clientDetailsService),
                    new RefreshTokenGranter(tokenServices, clientDetailsService),
                    new ResourceOwnerPasswordTokenGranter(authenticationManager(), tokenServices, clientDetailsService)
            ));
        }
        return tokenGranter;
    }


}
