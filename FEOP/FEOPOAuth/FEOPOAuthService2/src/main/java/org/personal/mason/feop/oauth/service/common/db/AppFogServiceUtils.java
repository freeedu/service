package org.personal.mason.feop.oauth.service.common.db;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class AppFogServiceUtils {
	private static final String VCAP_SERVICES = "VCAP_SERVICES";

	public static Map<String, AppFogServiceConfiguration> parse(String serviceString) {
		Map<String, AppFogServiceConfiguration> serviceConfigurations = new HashMap<String, AppFogServiceConfiguration>();

		try {
			String servicesString = System.getenv(VCAP_SERVICES);
			ObjectMapper mapper = new ObjectMapper();
			AppFogServicesConfiguration config = mapper.readValue(servicesString, AppFogServicesConfiguration.class);

			for (AppFogMysqlConfiguration configuration : config.getMysql()) {
				serviceConfigurations.put(configuration.getName(), configuration);
			}
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}

		return serviceConfigurations;
	}

}
