package org.personal.mason.feop.oauth.service.dao;

import org.personal.mason.feop.oauth.service.domain.OauthRefreshToken;

public interface OauthRefreshTokenDao extends GenericDao<OauthRefreshToken> {

	OauthRefreshToken findRefreshTokenWithTokenId(String tokenId);

	void removeRefreshTokenByTokenId(String extractTokenKey);

}
