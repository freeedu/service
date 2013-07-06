package org.personal.mason.feop.oauth.service.repository;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthRefreshTokenRepository extends JpaRepository<OauthRefreshToken, Long> {

	List<OauthRefreshToken> findByTokenId(String tokenid);

}
