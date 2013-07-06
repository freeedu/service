package org.personal.mason.feop.oauth.service.mvc.autologin;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

public class AutoLoginAuthenticationRequest implements Authentication {

	private static final long serialVersionUID = 1033744374135225968L;
	private final AutoLoginAuthenticationDetails details;
	private final Map<String, String> info;

	public AutoLoginAuthenticationRequest(AutoLoginAuthenticationDetails details, Map<String, String> info) {
		this.details = details;
		this.info = Collections.unmodifiableMap(info);
	}

	public AutoLoginAuthenticationRequest(String username, String password, AutoLoginAuthenticationDetails details) {
		Assert.hasText(username, "username cannot be empty");
		Assert.hasText(password, "password cannot be empty");
		this.details = details;
		Map<String, String> map = new HashMap<>();
		map.put("username", username);
		map.put("password", password);
		this.info = Collections.unmodifiableMap(map);
	}

	@Override
	public String getName() {
		return getPrincipal().toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public Object getCredentials() {
		return info.get("password");
	}

	@Override
	public Object getDetails() {
		return this.details;
	}

	@Override
	public Object getPrincipal() {
		return info.get("username");
	}

	@Override
	public boolean isAuthenticated() {
		return false;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException("Authentication request can not be 'authenticated'");
		}
	}

	public Map<String, String> getInfo() {
		return info;
	}
}
