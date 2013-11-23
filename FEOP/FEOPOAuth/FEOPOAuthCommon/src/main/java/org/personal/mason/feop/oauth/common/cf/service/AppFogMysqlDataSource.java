package org.personal.mason.feop.oauth.common.cf.service;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;

import java.util.logging.Logger;

public class AppFogMysqlDataSource extends DataSource {

Logger logger = Logger.getLogger(AppFogMysqlDataSource.class.getName());

private String serviceName;
private AppFogMysqlConfiguration serviceConfiguration;

/**
 * Constructor for reflection only. A default set of pool properties will be
 * created.
 */
public AppFogMysqlDataSource() {
	super();
}

/**
 * Constructs a DataSource object wrapping a connection
 * 
 * @param poolProperties
 */
public AppFogMysqlDataSource(PoolConfiguration poolProperties) {
	super(poolProperties);
}

public void setServiceName(String serviceName) {
	this.serviceName = serviceName;
	logger.info("set service name" + serviceName);
	initalize();
}

public String getServiceName() {
	return serviceName;
}

private void initalize() {
	logger.info("start initialize data source" + getServiceName());
	this.serviceConfiguration = (AppFogMysqlConfiguration) (VcapServices.getInstance()
			.getConfiguration(getServiceName()));
	logger.info("service configuration is null ? " + (this.serviceConfiguration == null));
	this.setUsername(serviceConfiguration.getCredentials().getUsername());
	this.setPassword(serviceConfiguration.getCredentials().getPassword());
	
	StringBuilder urlBuilder = new StringBuilder();
	urlBuilder.append("jdbc:mysql://");
	urlBuilder.append(serviceConfiguration.getCredentials().getHostname());
	urlBuilder.append(":");
	urlBuilder.append(serviceConfiguration.getCredentials().getPort());
	urlBuilder.append("/");
	urlBuilder.append(serviceConfiguration.getCredentials().getName());
	this.setUrl(urlBuilder.toString());
	logger.info("jdbc url is " + urlBuilder.toString());
}

}
