package org.personal.mason.feop.server.blog.client;

public class AuthorityInterceptor {

	private static String ALLOW = "allow";

	private String pattern;
	private boolean security = true;
	private String access = "";

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
	}

	public boolean needProcess(String url) {
		return url.matches(pattern) && security && !access.isEmpty() && !access.equals(ALLOW);
	}
}
