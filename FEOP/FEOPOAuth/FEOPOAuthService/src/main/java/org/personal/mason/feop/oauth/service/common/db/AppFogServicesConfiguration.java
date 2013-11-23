package org.personal.mason.feop.oauth.service.common.db;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class AppFogServicesConfiguration {

	@JsonProperty("mysql-5.1")
	private List<AppFogMysqlConfiguration> mysql;

	public List<AppFogMysqlConfiguration> getMysql() {
		return mysql;
	}

	public void setMysql(List<AppFogMysqlConfiguration> mysql) {
		this.mysql = mysql;
	}
}
