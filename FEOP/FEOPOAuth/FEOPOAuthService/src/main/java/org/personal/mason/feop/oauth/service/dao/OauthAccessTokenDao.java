package org.personal.mason.feop.oauth.service.dao;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthAccessToken;

public interface OauthAccessTokenDao extends GenericDao<OauthAccessToken> {

	OauthAccessToken findAccessTokenWithTokenId(String token);

	void removeAccessTokenByTokenId(String extractTokenKey);

	void removeAccessTokenByRefreshToken(String refreshToken);

	OauthAccessToken findAccessTokenWithAuthenticationId(String key);

	List<OauthAccessToken> findAccessTokenWithUsername(String userName);

	List<OauthAccessToken> findAccessTokenWithClientId(String clientId);

}
