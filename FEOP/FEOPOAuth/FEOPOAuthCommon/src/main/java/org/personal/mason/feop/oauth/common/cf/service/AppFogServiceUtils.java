package org.personal.mason.feop.oauth.common.cf.service;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AppFogServiceUtils {

	public static Map<String, AppFogServiceConfiguration> parse(String servicesString) {
		Map<String, AppFogServiceConfiguration> serviceConfigurations = new HashMap<>();

		try {
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
