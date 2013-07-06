package org.personal.mason.feop.oauth.service.spi.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.acls.model.NotFoundException;

public enum AuthorizationType {
	AuthorizationCode("authorization_code", "ROLE_CLIENT", "read,write", 60, 0), Implicit("implicit", "ROLE_CLIENT", "read,write", 60, 0), ResourceOwnerPassword(
			"password", "ROLE_CLIENT", "read,write", 60, 0), ClientCrdentials("client_credentials", "ROLE_CLIENT", "read,write", 60, 0), RefreshToken(
			"refresh_token", "ROLE_CLIENT", "read,write", 60, 0);

	private String grantType;
	private String authorities;
	private String scope;
	private int accessTokenValidity;
	private int refreshTokenValidity;

	private AuthorizationType(String grantType, String authorities, String scope, int accessTokenValidity, int refreshTokenValidity) {
		this.grantType = grantType;
		this.authorities = authorities;
		this.scope = scope;
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getGrantType() {
		return grantType;
	}

	public String getAuthorities() {
		return authorities;
	}

	public String getScope() {
		return scope;
	}

	public int getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public int getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public static List<String> getAllTypes() {
		return Arrays.asList(AuthorizationCode.name(), Implicit.name(), ResourceOwnerPassword.name(), ClientCrdentials.name(), RefreshToken.name());
	}

	public static AuthorizationType getClientTypeWithName(String name) {
		for (AuthorizationType type : AuthorizationType.values()) {
			if (type.name().endsWith(name)) {
				return type;
			}
		}

		throw new NotFoundException(String.format("Cannot found a type with name %s", name));
	}
}
