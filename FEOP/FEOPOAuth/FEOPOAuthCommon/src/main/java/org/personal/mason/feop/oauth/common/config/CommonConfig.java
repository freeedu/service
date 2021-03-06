package org.personal.mason.feop.oauth.common.config;

import org.personal.mason.feop.oauth.common.spi.SettingsHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/5/13
 * Time: 1:16 AM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ComponentScan(basePackages = {
        "org.personal.mason.feop.oauth.common"
})
@EnableJpaRepositories(basePackages = {"org.personal.mason.feop.oauth.common.domain.repository"})
@EnableTransactionManagement
public class CommonConfig {

}
