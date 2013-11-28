package org.personal.mason.feop.oauth.service.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.personal.mason.feop.oauth.common.cf.service.AppFogMysqlDataSource;
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
@Profile("prod")
@PropertySource(value = {"classpath:app.properties"})
public class ProdDataSources {

    @Value("${mysql.jdbc.driverClassName}")
    private String oauth2DBDriverClassName;
    @Value("${oauth2.service.name}")
    private String oauth2ServiceName;

    @Bean
    public DataSource oauthDataSource() {
        AppFogMysqlDataSource dataSource = new AppFogMysqlDataSource();
        dataSource.setDriverClassName(oauth2DBDriverClassName);
        dataSource.setServiceName(oauth2ServiceName);
        return dataSource;
    }
}
