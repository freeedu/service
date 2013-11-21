package org.personal.mason.feop.oauth.common.client;

import java.util.ArrayList;
import java.util.List;

public class AuthorityInterceptor {

	private static String ALLOW = "allow";
	public static String DENIED = "denyAll";

	public static AuthorityInterceptor defaultInterceptor;

	static {
		defaultInterceptor = new AuthorityInterceptor();
		defaultInterceptor.pattern = ".*";
		defaultInterceptor.access = DENIED;
	}

	private String pattern;
	private boolean security = true;
	private String access = "";
	private List<String> roles = new ArrayList<String>();

	public AuthorityInterceptor() {
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public boolean isSecurity() {
		return security;
	}

	public void setSecurity(boolean security) {
		this.security = security;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
		processRoles();
	}

	private void processRoles() {
		if (access != null) {
			String[] roleNames = access.split(",[\\s]*");
			for (String role : roleNames) {
				this.roles.add(role);
			}
		}
	}

	@Deprecated
	public boolean needProcess(String url) {
		return url.matches(pattern) && security && !access.isEmpty() && !access.equals(ALLOW);
	}

	public boolean canProcess(String uri) {
		if (uri == null) {
			return false;
		}

		return uri.matches(pattern);
	}

	public boolean hasRoleControl() {
		return security && !access.isEmpty() && !access.equals(ALLOW);
	}

	public List<String> getRequiredRoles() {
		return roles;
	}
}
