package org.personal.mason.feop.oauth.service.domain.service.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthAccessToken;

import java.util.List;

public interface FeopAccessTokenService {

    OauthAccessToken findAccessTokenWithTokenId(String extractTokenKey);

    void saveAccessToken(OauthAccessToken accessToken);

    void removeAccessTokenByTokenId(String extractTokenKey);

    void removeAccessTokenByRefreshToken(String extractTokenKey);

    OauthAccessToken findAccessTokenWithAuthenticationId(String key);

    List<OauthAccessToken> findAccessTokenWithUsername(String userName);

    List<OauthAccessToken> findAccessTokenWithClientId(String clientId);

    void deleteWithTokenId(String tokenId);

    void deleteWithRefreshToken(String refreshTokenId);
}
