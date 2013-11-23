package org.personal.mason.feop.oauth.common.cf.service;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

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
