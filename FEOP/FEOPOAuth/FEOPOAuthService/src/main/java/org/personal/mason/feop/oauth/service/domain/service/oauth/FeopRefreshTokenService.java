package org.personal.mason.feop.oauth.service.domain.service.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthRefreshToken;

public interface FeopRefreshTokenService {

    void saveRefreshToken(OauthRefreshToken token);

    OauthRefreshToken findRefreshTokenWithTokenId(String extractTokenKey);

    void removeRefreshTokenByTokenId(String extractTokenKey);

}
