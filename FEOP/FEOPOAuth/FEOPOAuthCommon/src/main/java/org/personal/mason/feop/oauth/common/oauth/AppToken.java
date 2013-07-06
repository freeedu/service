package org.personal.mason.feop.oauth.common.oauth;

import java.util.Set;

public class AppToken {
	private String username;
	private long expire;
	private Set<String> scope;
	private String clientId;
	private Set<String> aud;
	private Set<String> userRoles;
	private Set<String> clientRoles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	public Set<String> getScope() {
		return scope;
	}

	public void setScope(Set<String> scope) {
		this.scope = scope;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Set<String> getAud() {
		return aud;
	}

	public void setAud(Set<String> aud) {
		this.aud = aud;
	}

	public Set<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<String> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<String> getClientRoles() {
		return clientRoles;
	}

	public void setClientRoles(Set<String> clientRoles) {
		this.clientRoles = clientRoles;
	}

}
