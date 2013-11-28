package org.personal.mason.feop.oauth.service.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 11/28/13
 * Time: 7:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@Profile("dev")
@PropertySource(value = {"classpath:app.properties"})
public class DevDataSources {
    @Value("${oauth2.jdbc.url}")
    private String oauth2DBUrl;
    @Value("${mysql.jdbc.driverClassName}")
    private String oauth2DBDriverClassName;
    @Value("${oauth2.jdbc.username}")
    private String oauth2DBUsername;
    @Value("${oauth2.jdbc.password}")
    private String oauth2DBPassword;

    @Bean
    public DataSource oauthDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(oauth2DBDriverClassName);
        dataSource.setUrl(oauth2DBUrl);
        dataSource.setUsername(oauth2DBUsername);
        dataSource.setPassword(oauth2DBPassword);
        return dataSource;
    }
}
