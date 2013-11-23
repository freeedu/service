package org.personal.mason.feop.oauth.common.cf.service;

import java.util.List;

public class AppFogMysqlConfiguration implements AppFogServiceConfiguration {
	
	private String name;
	private String label;
	private String plan;
	private List<String> tags;
	private AppFogMysqlCredentials credentials;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public AppFogMysqlCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(AppFogMysqlCredentials credentials) {
		this.credentials = credentials;
	}

}
