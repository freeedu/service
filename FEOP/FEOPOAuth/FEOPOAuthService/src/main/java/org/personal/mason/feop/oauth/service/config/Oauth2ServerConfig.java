package org.personal.mason.feop.oauth.service.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.personal.mason.feop.oauth.common.cf.service.AppFogMysqlDataSource;
import org.personal.mason.feop.oauth.service.common.oauth2.OAuth2UserDetailsServiceImpl;
import org.personal.mason.feop.oauth.service.config.oauth.Oauth2ConfigAdapter;
import org.personal.mason.feop.oauth.service.config.oauth.Oauth2ServerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.approval.TokenServicesUserApprovalHandler;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.JdbcTokenStore;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 11/29/13
 * Time: 12:38 AM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@Order(1)
public class Oauth2ServerConfig extends Oauth2ConfigAdapter{
    private static final String RESOURCE_ID = "sparklr";

    @Qualifier("oauthDataSource")
    @Autowired
    private DataSource oauthDataSource;


    @Bean
    public JdbcClientDetailsService clientDetails(){
        return new JdbcClientDetailsService(oauthDataSource);
    }

    @Bean
    public JdbcTokenStore tokenStore(){
        return new JdbcTokenStore(oauthDataSource);
    }

    @Bean
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetails());

        return tokenServices;
    }

    @Bean
    public TokenServicesUserApprovalHandler userApprovalHandler(){
        TokenServicesUserApprovalHandler approvalHandler = new TokenServicesUserApprovalHandler();
        approvalHandler.setTokenServices(tokenServices());
        return approvalHandler;
    }

    @Bean
    public ClientDetailsUserDetailsService clientDetailsUserDetailsService(){
        return new ClientDetailsUserDetailsService(clientDetails());
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new OAuth2UserDetailsServiceImpl();
    }

//    @Bean
//    public

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers("/resources/**").and()
                .ignoring().antMatchers("/account/findpassword", "/account/resetpassword");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    }

    //    <security:http pattern="/oauth/token" create-session="stateless"
//    authentication-manager-ref="clientAuthenticationManager">
//    <security:intercept-url pattern="/oauth/token" access="IS_AUTHENTICATED_FULLY"/>
//    <security:anonymous enabled="false"/>
//    <security:http-basic/>
//    <!-- include this only if you need to authenticate clients via request parameters -->
//    <security:custom-filter ref="clientCredentialsTokenEndpointFilter" after="BASIC_AUTH_FILTER"/>
//    <security:access-denied-handler error-page="/login"/>
//    </security:http>
//
//    <security:http pattern="/userinfo" entry-point-ref="oauthAuthenticationEntryPoint"
//    access-denied-page="/login">
//    <security:intercept-url pattern="/userinfo" access="IS_AUTHENTICATED_FULLY"/>
//    <security:custom-filter ref="resourceServerFilter" before="PRE_AUTH_FILTER"/>
//    </security:http>
//
//    <security:http pattern="/client/**" entry-point-ref="oauthAuthenticationEntryPoint">
//    <security:anonymous enabled="false"/>
//    <security:intercept-url pattern="/client/**" access="ROLE_USER"/>
//    <security:custom-filter ref="resourceServerFilter" before="PRE_AUTH_FILTER"/>
//    <security:access-denied-handler error-page="/login"/>
//    </security:http>
//
//    <security:http pattern="/account/**" entry-point-ref="oauthAuthenticationEntryPoint">
//    <security:anonymous enabled="false"/>
//    <security:intercept-url pattern="/account/**" access="ROLE_USER"/>
//    <security:custom-filter ref="resourceServerFilter" before="PRE_AUTH_FILTER"/>
//    <security:access-denied-handler error-page="/login"/>
//    </security:http>
//
//    <security:http pattern="/me/**" entry-point-ref="oauthAuthenticationEntryPoint">
//    <security:anonymous enabled="false"/>
//    <security:intercept-url pattern="/me/**" access="ROLE_USER"/>
//    <security:custom-filter ref="resourceServerFilter" before="PRE_AUTH_FILTER"/>
//    <security:access-denied-handler error-page="/login"/>
//    </security:http>
//
//
//    <security:http auto-config="true" use-expressions="true">
//    <security:intercept-url pattern="/home*" access="hasRole('ROLE_USER')"/>
//
//    <security:intercept-url pattern="/oauth/**"
//    access="hasAnyRole('ROLE_CLIENT','ROLE_USER','ROLE_ADMIN')"/>
//    <security:intercept-url pattern="/admin/**" access="hasRole('ROLE_ADMIN')"/>
//    <security:intercept-url pattern="/**" access="permitAll"/>
//
//    <security:form-login authentication-failure-url="/login?error=true"
//    default-target-url="/home" login-page="/login" login-processing-url="/oauth/login.do"/>
//    <security:logout logout-url="/oauth/logout.do" invalidate-session="true"
//    success-handler-ref="logoutSuccessHandler"/>
//
//    <security:custom-filter ref="resourceServerFilter" before="PRE_AUTH_FILTER"/>
//    <security:expression-handler ref="oauthWebExpressionHandler"/>
//    </security:http>
//
//    <bean id="logoutSuccessHandler"
//    class="org.personal.mason.feop.oauth.service.common.security.FEOPLogoutSuccessHandler">
//    </bean>


}
