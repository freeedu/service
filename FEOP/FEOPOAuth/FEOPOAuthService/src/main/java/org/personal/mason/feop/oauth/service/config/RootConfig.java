package org.personal.mason.feop.oauth.service.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.personal.mason.feop.oauth.service.common.mail.EmailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
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
        "org.personal.mason.feop.oauth.service",
        "org.personal.mason.feop.oauth.common"
})
@EnableJpaRepositories(basePackages = {"org.personal.mason.feop.oauth.service.domain.repository", "org.personal.mason.feop.oauth.common.domain.repository"})
@PropertySource(value = {"classpath:app.properties"})
@EnableTransactionManagement
public class RootConfig {

    @Value("${oauth2.jdbc.url}")
    private String oauthDBUrl;
    @Value("${mysql.jdbc.driverClassName}")
    private String oauthDBDriverClassName;
    @Value("${oauth2.jdbc.username}")
    private String oauthDBUsername;
    @Value("${oauth2.jdbc.password}")
    private String oauthDBPassword;
    @Value("${oauth2.service.name}")
    private String oauthServiceName;
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
    @Value("${packages.to.scan}")
    private String[] packagesToScan;
    @Value("${mail.host}")
    private String mailHost;
    @Value("${mail.username}")
    private String mailUsername;
    @Value("${mail.password}")
    private String mailPassword;
    @Value("${mail.transport.protocol}")
    private String mailProtocol;
    @Value("${mail.smtp.auth}")
    private String  mailAuth;
    @Value("${mail.smtp.ssl.enable}")
    private String mailEnableSSL;
    @Value("${mail.smtp.starttls.enable}")
    private String  mailEnableTTL;
    @Value("${mail.debug}")
    private String mailDebug;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(oauthDBDriverClassName);
        dataSource.setUrl(oauthDBUrl);
        dataSource.setUsername(oauthDBUsername);
        dataSource.setPassword(oauthDBPassword);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPersistenceUnitName(persistenceUnitName);
        emf.setPackagesToScan(packagesToScan);
        emf.setJpaVendorAdapter(jpaVendorAdapter());
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


    // Common Beans
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new StandardPasswordEncoder();
    }

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);
        Properties mailProperties = new Properties();
        mailProperties.put("mail.transport.protocol", mailProtocol);
        mailProperties.put("mail.smtp.auth", mailAuth);
        mailProperties.put("mail.smtp.ssl.enable", mailEnableSSL);
        mailProperties.put("mail.smtp.starttls.enable", mailEnableTTL);
        mailProperties.put("mail.debug", mailDebug);
        mailSender.setJavaMailProperties(mailProperties);
        return mailSender;
    }

    @Bean
    public EmailSender emailSender(){
        EmailSender emailSender = new EmailSender();
        emailSender.setSender(mailUsername);
        emailSender.setJavaMailSender(javaMailSender());
        return emailSender;
    }

}
