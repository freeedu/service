package org.personal.mason.feop.oauth.service.domain.repository.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OauthRefreshTokenRepository extends JpaRepository<OauthRefreshToken, Long> {

    List<OauthRefreshToken> findByTokenId(String tokenid);

}
