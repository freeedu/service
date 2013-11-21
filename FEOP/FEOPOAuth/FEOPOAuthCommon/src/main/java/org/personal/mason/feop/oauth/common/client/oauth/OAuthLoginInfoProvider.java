package org.personal.mason.feop.oauth.common.client.oauth;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.personal.mason.feop.oauth.common.client.ClientConfiguration;
import org.personal.mason.feop.oauth.common.model.UserInfo;
import org.personal.mason.feop.oauth.common.utils.Constrains;
import org.springframework.http.MediaType;

public abstract class OAuthLoginInfoProvider {
	protected static final String STATE = "state";

	private ClientConfiguration configuration;
	protected StateKeyGenerator stateKeyGenerator = new DefaultStateKeyGenerator();

	public void setConfiguration(ClientConfiguration configuration) {
		this.configuration = configuration;
	}

	public void setStateKeyGenerator(StateKeyGenerator stateKeyGenerator) {
		this.stateKeyGenerator = stateKeyGenerator;
	}

	public abstract String getAuthorizationRequestUrl(HttpServletRequest request);

	public abstract void processAccessToken(HttpServletRequest request, HttpServletResponse response);

	public abstract String getAccessTokenRequestUrl(String callback);

	public abstract boolean isDirectlyRequestToken(HttpServletRequest request);

	public void retrieveUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		FEOPAuthentication auth = (FEOPAuthentication) session.getAttribute(Constrains.AUTHENTICATIOIN);

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
		FEOPAuthentication authentication = (FEOPAuthentication) session.getAttribute(Constrains.AUTHENTICATIOIN);
		if (authentication != null && authentication.hasValidToken()) {
			return true;
		}
		return false;
	}

}
