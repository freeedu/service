package org.personal.mason.feop.oauth.service.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 11/28/13
 * Time: 7:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Qualifier("oauthDataSource")
    @Autowired
    private DataSource oauthDataSource;

    @Qualifier("passwordEncoder")
    @Autowired
    private PasswordEncoder passwordEncoder;


//    public AuthenticationManager authManager() throws Exception {
//        return new AuthenticationManagerBuilder().
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.jdbcAuthentication().dataSource(oauthDataSource).passwordEncoder(passwordEncoder);
        auth.inMemoryAuthentication().withUser("mason").password("mason").roles("USER").and()
                .withUser("mmei").password("mmei").roles("ADMIN");
    }

//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return authManager();
//    }

    @Override
    protected void configure(HttpSecurity builder) throws Exception {
        //builder.authorizeRequests().antMatchers("/oauth2/**").hasRole("USER").anyRequest().permitAll().and(;
        super.configure(builder);
    }
}
