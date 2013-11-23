package org.personal.mason.feop.oauth.service.common.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.cloudfoundry.runtime.service.CloudServicesScanner;

public class VcapServices {
Logger logger = Logger.getLogger(CloudServicesScanner.class.getName());

private static VcapServices vcapServices;

private Map<String, AppFogServiceConfiguration> serverConfigurations;

private VcapServices() {
	initServices();
}

private void initServices() {
	String serviceString = System.getenv("VCAP_SERVICES");
	logger.info("services are " + serviceString);
	if (serviceString != null && serviceString.length() > 0) {
		serverConfigurations = AppFogServiceUtils.parse(serviceString);
	}
}

/**
 * Singleton instance construction method
 * 
 * @return
 */
public synchronized static VcapServices getInstance() {
	if (vcapServices == null) {
		vcapServices = new VcapServices();
	}
	return vcapServices;
}

/**
 * Retrieve given type service configurations
 * 
 * @param serviceType
 * @return
 */
public List<AppFogServiceConfiguration> getConfigurations(AppFogServiceType serviceType) {
	List<AppFogServiceConfiguration> configurations = new ArrayList<>();
	
	for (AppFogServiceConfiguration configuration : serverConfigurations.values()) {
		if (configuration.getClass() == serviceType.getInstanceType()) {
			configurations.add(configuration);
		}
	}
	return configurations;
}

/**
 * get the configuration with the given name;
 * 
 * @param name
 * @return
 */
public AppFogServiceConfiguration getConfiguration(String name) {
	return serverConfigurations.get(name);
}

public Map<String, AppFogMysqlConfiguration> getConfigurationMaps(AppFogServiceType serviceType) {
	Map<String, AppFogMysqlConfiguration> mysqlConfigurations = new HashMap<String, AppFogMysqlConfiguration>();
	
	for (Entry<String, AppFogServiceConfiguration> entry : serverConfigurations.entrySet()) {
		if (entry.getValue().getClass() == serviceType.getInstanceType()) {
			mysqlConfigurations.put(entry.getKey(), (AppFogMysqlConfiguration) entry.getValue());
		}
	}
	
	return mysqlConfigurations;
}

}
