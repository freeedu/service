package org.personal.mason.feop.oauth.service.domain.repository.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OauthAccessTokenRepository extends JpaRepository<OauthAccessToken, Long> {
    List<OauthAccessToken> findByTokenId(String tokenid);

    List<OauthAccessToken> findByRefreshToken(String refreshtoken);

    List<OauthAccessToken> findByAuthenticationId(String authenticationid);

    List<OauthAccessToken> findByClientId(String clientid);

    List<OauthAccessToken> findByUserName(String username);
}
