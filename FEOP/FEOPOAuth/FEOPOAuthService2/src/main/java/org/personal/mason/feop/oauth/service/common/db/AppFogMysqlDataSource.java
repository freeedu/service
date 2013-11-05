package org.personal.mason.feop.oauth.service.common.db;

import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.jdbc.pool.DataSource;

public class AppFogMysqlDataSource extends DataSource {
	private static Map<String, AppFogMysqlConfiguration> MYSQL_PROPS = new HashMap<>();

	static {
		MYSQL_PROPS.putAll(VcapServices.getInstance().getConfigurationMaps(AppFogServiceType.MySQLService));
	}

}
