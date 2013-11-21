package org.personal.mason.feop.oauth.contact.config;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.personal.mason.feop.oauth.contact" })
@EnableJpaRepositories(basePackages = "org.personal.mason.feop.oauth.contact.domain.repository")
@ImportResource("classpath:contact.properties")
public class Application extends WebMvcConfigurerAdapter {
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
private boolean generateDdl;
@Value("${jpa.showSql}")
private boolean showSql;
@Value("${jpa.database}")
private Database database;
@Value("${persistence.unit.name}")
private String persistenceUnitName;
@Value("${package.to.scan}")
private String packagesToScan;

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
public EntityManagerFactory entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
	emf.setDataSource(dataSource());
	emf.setPersistenceUnitName(persistenceUnitName);
	emf.setPackagesToScan(packagesToScan);
	emf.setJpaVendorAdapter(jpaVendorAdapter());
	return emf.getObject();
}

@Bean
public JpaVendorAdapter jpaVendorAdapter() {
	HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
	adapter.setGenerateDdl(generateDdl);
	adapter.setShowSql(showSql);
	adapter.setDatabase(database);
	return adapter;
}

@Bean
public JpaTransactionManager transactionManager() {
	JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(entityManagerFactory());
	return transactionManager;
}

@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
			.setCachePeriod(31556926);
	super.addResourceHandlers(registry);
}

@Bean
public InternalResourceViewResolver internalResolver() {
	InternalResourceViewResolver internalResolver = new InternalResourceViewResolver();
	internalResolver.setPrefix("/WEB-INF/views/");
	internalResolver.setSuffix(".jsp");
	return internalResolver;
}

}
