package org.personal.mason.feop.oauth.service.config;

import org.personal.mason.feop.oauth.service.common.oauth2.OAuth2UserDetailsServiceImpl;
import org.personal.mason.feop.oauth.service.common.security.FEOPLogoutSuccessHandler;
import org.personal.mason.feop.oauth.service.config.oauth.OAuth2ServerConfigurer;
import org.personal.mason.feop.oauth.service.config.oauth.OAuth2ServerConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.approval.TokenServicesUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/6/13
 * Time: 1:14 AM
 * To change this template use File | Settings | File Templates.
 */

@Configuration
@Import({RootConfig.class})
@Order(1)
public class OAuth2ServerConfig extends OAuth2ServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientDetailsUserDetailsService clientDetailsUserService;
    @Autowired
    private OAuth2UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
                .and().userDetailsService(clientDetailsUserService);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    //@DependsOn("springSecurityFilterChain") // FIXME remove the need for @DependsOn
    public UserApprovalHandler userApprovalHandler() throws Exception {
        TokenServicesUserApprovalHandler userApprovalHandler = new TokenServicesUserApprovalHandler();
        userApprovalHandler.setTokenServices(tokenServices());
        return userApprovalHandler;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/account/findpassword", "/account/resetpassword","/signup/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/token/**").hasRole("USER")
                //.anyRequest().permitAll()
                .and().exceptionHandling().accessDeniedPage("/login?error=true")
                .and().logout()
                .logoutUrl("/oauth/logout.do")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(logoutSuccessHandler())
                .and().formLogin()
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/home")
                .loginProcessingUrl("/oauth/login.do")
                .loginPage("/login");
        http.anonymous().disable()
            .authorizeRequests()
                .antMatchers("/oauth/token").fullyAuthenticated()
                .regexMatchers("/client/.*", "/account/.*", "/me/.*", "/home.*").hasRole("USER")
                //.antMatchers("/client/**", "/account/**", "/me/**", "/home*").hasRole("USER")
                .antMatchers("/oauth/**").hasAnyRole("CLIENT", "USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().exceptionHandling().accessDeniedPage("/login?error=true")
                .and().apply(new OAuth2ServerConfigurer(authenticationManager()));
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new FEOPLogoutSuccessHandler();
    }
}
