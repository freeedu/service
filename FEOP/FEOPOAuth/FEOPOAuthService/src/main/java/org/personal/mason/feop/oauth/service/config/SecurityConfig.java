package org.personal.mason.feop.oauth.service.config;

import org.personal.mason.feop.oauth.service.common.oauth2.OAuth2UserDetailsServiceImpl;
import org.personal.mason.feop.oauth.service.common.oauth2.extention.FOEPAuthorizationCodeServices;
import org.personal.mason.feop.oauth.service.common.security.FEOPLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.DefaultAuthorizationRequestManager;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpointHandlerMapping;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.vote.ScopeVoter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.preauth.j2ee.J2eePreAuthenticatedProcessingFilter;

import java.util.Arrays;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 12/5/13
 * Time: 12:58 PM
 * To change this template use File | Settings | File Templates.
 */

@Import(OAuthConfig.class)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Qualifier("clientDetailsService")
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Qualifier("userApprovalHandler")
    @Autowired
    private UserApprovalHandler userApprovalHandler;
    @Qualifier("tokenServices")
    @Autowired
    private DefaultTokenServices tokenServices;
    @Qualifier("passwordEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Qualifier("clientDetailsUserService")
    @Autowired
    private ClientDetailsUserDetailsService clientDetailsUserService;
    @Qualifier("userDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;
    @Qualifier("resourceServerFilter")
    @Autowired
    private OAuth2AuthenticationProcessingFilter resourceServerFilter;

    @Bean
    public AuthorizationEndpoint authorizationEndpoint() throws Exception {
        AuthorizationEndpoint endpoint = new AuthorizationEndpoint();
        endpoint.setClientDetailsService(clientDetailsService);
        endpoint.setUserApprovalHandler(userApprovalHandler);
        endpoint.setTokenGranter(tokenGranters());
        endpoint.setAuthorizationCodeServices(authorizationCodeServices());
        endpoint.setAuthorizationRequestManager(authorizationRequestManager());
        return endpoint;
    }

    @Bean
    public FrameworkEndpointHandlerMapping frameworkEndpointHandlerMapping(){
        FrameworkEndpointHandlerMapping endpointHandlerMapping = new FrameworkEndpointHandlerMapping();
        ManagedMap<String, String> mappings = new ManagedMap<>();
        mappings.put("/oauth/token", "/oauth/token");
        mappings.put("/oauth/authorize", "/oauth/authorize");
        endpointHandlerMapping.setMappings(mappings);
        return endpointHandlerMapping;
    }

    @Bean
    public FOEPAuthorizationCodeServices authorizationCodeServices(){
        FOEPAuthorizationCodeServices authorizationCodeServices = new FOEPAuthorizationCodeServices();
        return authorizationCodeServices;
    }

    @Bean
    public DefaultAuthorizationRequestManager authorizationRequestManager(){
        DefaultAuthorizationRequestManager requestManager = new DefaultAuthorizationRequestManager(clientDetailsService);
        return requestManager;
    }

    @Bean
    public TokenEndpoint tokenEndpoint() throws Exception {
        TokenEndpoint endpoint = new TokenEndpoint();
        endpoint.setTokenGranter(tokenGranters());
        endpoint.setAuthorizationRequestManager(authorizationRequestManager());
        endpoint.setClientDetailsService(clientDetailsService);
        return endpoint;
    }

    @Bean
    public TokenGranter tokenGranters() throws Exception {
        CompositeTokenGranter tokenGranter = new CompositeTokenGranter(Arrays.<TokenGranter>asList(
                new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices(), clientDetailsService),
                new ImplicitTokenGranter(tokenServices, clientDetailsService),
                new ClientCredentialsTokenGranter(tokenServices, clientDetailsService),
                new RefreshTokenGranter(tokenServices, clientDetailsService),
                new ResourceOwnerPasswordTokenGranter(authenticationManager(), tokenServices, clientDetailsService)
        ));
        return tokenGranter;
    }

    @Bean
    public ClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter() throws Exception {
        ClientCredentialsTokenEndpointFilter filter = new ClientCredentialsTokenEndpointFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean(name = "oauthAuthenticationEntryPoint")
    public OAuth2AuthenticationEntryPoint oauthAuthenticationEntryPoint(){
        OAuth2AuthenticationEntryPoint entryPoint = new OAuth2AuthenticationEntryPoint();
        entryPoint.setRealmName("foep");
        return entryPoint;
    }

    @Bean(name = "clientAuthenticationEntryPoint")
    public OAuth2AuthenticationEntryPoint clientAuthenticationEntryPoint(){
       OAuth2AuthenticationEntryPoint clientEntryPoint = new OAuth2AuthenticationEntryPoint();
        clientEntryPoint.setRealmName("foep/client");
        clientEntryPoint.setTypeName("Basic");
        return clientEntryPoint;
    }

    @Bean
    public OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler(){
        return new OAuth2AccessDeniedHandler();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager(){
        UnanimousBased accessDecisionManager = new UnanimousBased(Arrays.<AccessDecisionVoter>asList(
                new ScopeVoter(),
                new RoleVoter(),
                new AuthenticatedVoter()
        ));
        return accessDecisionManager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
                .and().userDetailsService(clientDetailsUserService);
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new FEOPLogoutSuccessHandler();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/account/findpassword", "/account/resetpassword") ;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().antMatchers("/oauth/token").fullyAuthenticated().
        //http.userDetailsService(userDetailsService).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).

        http.authorizeRequests().antMatchers("/client/**", "/account/**", "/me/**", "/home*").hasRole("USER")
                .antMatchers("/oauth/**").hasAnyRole("CLIENT","USER","ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll();

        http.formLogin().failureUrl("/login?error=true").defaultSuccessUrl("/home").loginProcessingUrl("/oauth/login.do") .loginPage("/login");

        http.logout().logoutUrl("/oauth/logout.do").invalidateHttpSession(true).logoutSuccessHandler(logoutSuccessHandler());

        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
        http.addFilterBefore(resourceServerFilter, J2eePreAuthenticatedProcessingFilter.class);

    }

    /*
    <security:http pattern="/oauth/token" create-session="stateless"
    authentication-manager-ref="authenticationManager">
    <security:intercept-url pattern="/oauth/token" access="IS_AUTHENTICATED_FULLY"/>
    <security:anonymous enabled="false"/>
    <security:http-basic entry-point-ref="clientAuthenticationEntryPoint"/>
    <!-- include this only if you need to authenticate clients via request parameters -->
    <security:custom-filter ref="clientCredentialsTokenEndpointFilter" after="BASIC_AUTH_FILTER"/>
    <security:access-denied-handler error-page="/login"/>
    </security:http>

    <security:http pattern="/userinfo" entry-point-ref="oauthAuthenticationEntryPoint"
    access-denied-page="/login">
    <security:intercept-url pattern="/userinfo" access="IS_AUTHENTICATED_FULLY"/>
    <security:custom-filter ref="resourceServerFilter" before="PRE_AUTH_FILTER"/>
    </security:http>

    <security:expression-handler ref="oauthWebExpressionHandler"/>
    </security:http>


    */
}
