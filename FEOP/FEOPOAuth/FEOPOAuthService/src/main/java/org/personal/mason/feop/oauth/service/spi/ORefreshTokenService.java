package org.personal.mason.feop.oauth.service.spi;

import org.personal.mason.feop.oauth.service.domain.OauthRefreshToken;

public interface ORefreshTokenService {

	void saveObject(OauthRefreshToken token);

	OauthRefreshToken findRefreshTokenWithTokenId(String extractTokenKey);

	void removeRefreshTokenByTokenId(String extractTokenKey);

}
