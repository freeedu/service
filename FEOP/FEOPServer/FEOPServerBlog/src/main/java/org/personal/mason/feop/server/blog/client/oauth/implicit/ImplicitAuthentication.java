package org.personal.mason.feop.server.blog.client.oauth.implicit;

import java.util.Calendar;
import java.util.Map;

import org.personal.mason.feop.oauth.common.model.UserInfo;
import org.personal.mason.feop.server.blog.client.oauth.FEOPAuthentication;

public class ImplicitAuthentication implements FEOPAuthentication {
	private static final String ACCESS_TOKEN = "access_token";
	private static final String TOKEN_TYPE = "token_type";
	private static final String EXPIRES_IN = "expires_in";
	private static final String SCOPE = "scope";
	private static final String STATE = "state";

	private long createTime;
	private String accessToken;
	private String tokenType;
	private int expiresIn;
	private String scope;
	private UserInfo userInfo;

	public ImplicitAuthentication(Map<String, String> properties) {
		createTime = Calendar.getInstance().getTimeInMillis() / 1000;
		accessToken = properties.get(ACCESS_TOKEN);
		tokenType = properties.get(TOKEN_TYPE);
		String expiresInString = properties.get(EXPIRES_IN);
		expiresIn = Integer.valueOf(expiresInString == null ? "0" : expiresInString);
		scope = properties.get(SCOPE);
	}

	@Override
	public boolean hasValidToken() {
		return accessToken != null;
	}

	@Override
	public boolean hasRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTokenExpired() {
		return accessToken != null && (Calendar.getInstance().getTimeInMillis() / 1000) > createTime + expiresIn;
	}

	@Override
	public String getRefreshToken() {
		throw new UnsupportedOperationException("Not support operation");
	}

	@Override
	public String getTokenType() {
		return tokenType;
	}

	@Override
	public String getScope() {
		return scope;
	}

	@Override
	public String getAccessToken() {
		return accessToken;
	}

	@Override
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public UserInfo getUserInfo() {
		return userInfo;
	}

}
