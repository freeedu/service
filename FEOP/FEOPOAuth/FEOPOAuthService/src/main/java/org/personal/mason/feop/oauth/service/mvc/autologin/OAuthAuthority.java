package org.personal.mason.feop.oauth.service.mvc.autologin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum OAuthAuthority implements GrantedAuthority {
	OAUTH_ADMIN("oauth.admin", 1), OAUTH_USER("oauth.user", 0), OAUTH_NONE("oauth.none", -1);

	public static final List<OAuthAuthority> ADMIN_AUTHORITIES = Collections.unmodifiableList(Arrays.asList(OAUTH_ADMIN, OAUTH_USER));
	public static final List<OAuthAuthority> USER_AUTHORITIES = Collections.unmodifiableList(Arrays.asList(OAUTH_USER));
	public static final List<OAuthAuthority> NONE_AUTHORITIES = Collections.unmodifiableList(Arrays.asList(OAUTH_NONE));

	private int value;
	private String userType;

	private OAuthAuthority(String userType, int value) {
		this.value = value;
		this.userType = userType;
	}

	public int getValue() {
		return value;
	}

	public String getUserType() {
		return userType;
	}

	@Override
	public String getAuthority() {
		return userType;
	}

	public static OAuthAuthority fromAuthorities(String authorities) {
		String type = authorities == null ? "oauth.user" : authorities.toLowerCase();
		return type.contains("oauth.admin") ? OAUTH_ADMIN : OAUTH_USER;
	}

	public static GrantedAuthority authority(String value) {
		return value.contains("oauth.admin") ? OAUTH_ADMIN : value.contains("oauth.admin") ? OAUTH_USER : new SimpleGrantedAuthority(value);
	}

}
