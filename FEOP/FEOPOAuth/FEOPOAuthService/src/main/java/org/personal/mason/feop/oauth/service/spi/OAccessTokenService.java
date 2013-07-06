package org.personal.mason.feop.oauth.service.spi;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthAccessToken;

public interface OAccessTokenService {

	OauthAccessToken findAccessTokenWithTokenId(String extractTokenKey);

	void saveObject(OauthAccessToken accessToken);

	void removeAccessTokenByTokenId(String extractTokenKey);

	void removeAccessTokenByRefreshToken(String extractTokenKey);

	OauthAccessToken findAccessTokenWithAuthenticationId(String key);

	List<OauthAccessToken> findAccessTokenWithUsername(String userName);

	List<OauthAccessToken> findAccessTokenWithClientId(String clientId);

}
