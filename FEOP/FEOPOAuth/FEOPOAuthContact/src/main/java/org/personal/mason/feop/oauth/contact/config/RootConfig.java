package org.personal.mason.feop.oauth.contact.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.search.store.impl.RAMDirectoryProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 11/23/13
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */

@Configuration
@ComponentScan(basePackages = {
        "org.personal.mason.feop.oauth.contact.domain.repository",
        "org.personal.mason.feop.oauth.contact.spi.impl",
        "org.personal.mason.feop.oauth.contact.common"
})
@EnableJpaRepositories(basePackages = {"org.personal.mason.feop.oauth.contact.domain.repository"})
@PropertySource(value = {"classpath:contact.properties"})
@EnableTransactionManagement
public class RootConfig {

    @Value("${contact.jdbc.url}")
    private String contactDBUrl;
    @Value("${mysql.jdbc.driverClassName}")
    private String contactDBDriverClassName;
    @Value("${contact.jdbc.username}")
    private String contactDBUsername;
    @Value("${contact.jdbc.password}")
    private String contactDBPassword;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${jpa.generateDdl}")
    private Boolean generateDdl;
    @Value("${jpa.showSql}")
    private Boolean showSql;
    @Value("${jpa.database}")
    private String database;
    @Value("${persistence.unit.name}")
    private String persistenceUnitName;
    @Value("${package.to.scan}")
    private String[] packagesToScan;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(contactDBDriverClassName);
        dataSource.setUrl(contactDBUrl);
        dataSource.setUsername(contactDBUsername);
        dataSource.setPassword(contactDBPassword);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPersistenceUnitName(persistenceUnitName);
        emf.setPackagesToScan(packagesToScan);
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.search.default.directory_provider", new RAMDirectoryProvider());

        emf.setJpaProperties(jpaProperties);
        return emf;
    }

    private JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(getDatabase(database));
        adapter.setGenerateDdl(generateDdl);
        adapter.setShowSql(showSql);
        return adapter;
    }

    private Database getDatabase(String name) {
        for (Database db : Database.values()) {
            if (db.name().equalsIgnoreCase(name)) {
                return db;
            }
        }
        return null;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}
