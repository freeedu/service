package org.personal.mason.feop.oauth.common.cf.service;

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
