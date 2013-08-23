package org.personal.mason.feop.server.blog.client.oauth;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.map.ObjectMapper;
import org.personal.mason.feop.oauth.common.model.UserInfo;
import org.personal.mason.feop.server.blog.client.oauth.code.RestClient;
import org.springframework.http.MediaType;

public abstract class OAuthLoginInfoProvider {
	protected static final String AUTHENTICATIOIN = "authentication";

	private ClientConfiguration configuration;

	public void setConfiguration(ClientConfiguration configuration) {
		this.configuration = configuration;
	}

	public abstract String getAuthorizationRequestUrl(String callback);

	public abstract void processAccessToken(HttpServletRequest request, HttpServletResponse response);

	public abstract String getAccessTokenRequestUrl(String callback);

	public abstract boolean isDirectlyRequestToken(HttpServletRequest request);

	public void retrieveUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		FEOPAuthentication auth = (FEOPAuthentication) session.getAttribute(AUTHENTICATIOIN);

		if (auth != null) {
			StringBuilder urlPattern = new StringBuilder(configuration.getUserInfoUri());
			if (urlPattern.indexOf("?") > 0) {
				urlPattern.append("&access_token=%s");
			} else {
				urlPattern.append("?access_token=%s");
			}

			String uri = String.format(urlPattern.toString(), auth.getAccessToken());
			UserInfo userInfo = RestClient.getInstance().getResource(uri).accept(MediaType.APPLICATION_JSON_VALUE).get(UserInfo.class);

			auth.setUserInfo(userInfo);
		}

	}

	public ClientConfiguration getConfiguration() {
		return configuration;
	}

	// ===================================================================//
	public boolean isLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			return false;
		}
		FEOPAuthentication authentication = (FEOPAuthentication) session.getAttribute(AUTHENTICATIOIN);
		if (authentication != null && authentication.hasValidToken()) {
			return true;
		}
		return false;
	}

}
