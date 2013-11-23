package org.personal.mason.feop.oauth.service.domain.service.oauth;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthAccessToken;

public interface FeopAccessTokenService {

	OauthAccessToken findAccessTokenWithTokenId(String extractTokenKey);

	void saveObject(OauthAccessToken accessToken);

	void removeAccessTokenByTokenId(String extractTokenKey);

	void removeAccessTokenByRefreshToken(String extractTokenKey);

	OauthAccessToken findAccessTokenWithAuthenticationId(String key);

	List<OauthAccessToken> findAccessTokenWithUsername(String userName);

	List<OauthAccessToken> findAccessTokenWithClientId(String clientId);

}
