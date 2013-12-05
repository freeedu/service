package org.personal.mason.feop.oauth.service.config;

import org.personal.mason.feop.oauth.service.common.security.FEOPLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/6/13
 * Time: 1:29 AM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/token/**").hasRole("USER")
                .anyRequest().permitAll()
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
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new FEOPLogoutSuccessHandler();
    }
}
