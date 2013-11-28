package org.personal.mason.feop.oauth.service.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.personal.mason.feop.oauth.service.common.mail.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
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
 * User: domei
 * Date: 11/28/13
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */

@Configuration
@ComponentScan(basePackages = {"org.personal.mason.feop.oauth.service"})
@EnableJpaRepositories(basePackages = "org.personal.mason.feop.oauth.service.domain.repository")
@PropertySource(value = {"classpath:app.properties"})
@EnableTransactionManagement
public class RootConfig {
    private static final Logger logger = LoggerFactory.getLogger(RootConfig.class);

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
    private String packagesToScan;

    @Value("${mail.host}")
    private String mailHost;
    @Value("${mail.username}")
    private String mailUsername;
    @Value("${mail.password}")
    private String mailPassword;
    @Value("${mail.transport.protocol}")
    private String mailTransportProtocol;
    @Value("${mail.debug}")
    private String mailDebug;

    @Autowired
    @Qualifier(value = "oauthDataSource")
    DataSource oauthDataSource;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        StandardPasswordEncoder encoder = new StandardPasswordEncoder();
        return encoder;
    }

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);
        mailSender.setProtocol(mailTransportProtocol);
        Properties properties = new Properties();
        properties.put("mail.debug", mailDebug);
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }

    @Bean
    public EmailSender emailSender(){
        EmailSender sender = new EmailSender();
        sender.setJavaMailSender(javaMailSender());
        sender.setSender(mailUsername);
        return sender;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(oauthDataSource);
        emf.setPersistenceUnitName(persistenceUnitName);
        emf.setPackagesToScan(packagesToScan);
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        Properties jpaProperties = new Properties();
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

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
