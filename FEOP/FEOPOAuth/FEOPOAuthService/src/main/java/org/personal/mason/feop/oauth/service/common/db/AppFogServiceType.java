package org.personal.mason.feop.oauth.service.common.db;

public enum AppFogServiceType {
	MySQLService(AppFogMysqlConfiguration.class);

	private Class<?> instanceType;

	private AppFogServiceType(Class<?> instanceType) {
		this.instanceType = instanceType;
	}

	public Class<?> getInstanceType() {
		return instanceType;
	}
}
