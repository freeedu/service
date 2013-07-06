package org.personal.mason.feop.oauth.service.repository;

import org.personal.mason.feop.oauth.service.domain.OauthClientToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthClientTokenRepository extends JpaRepository<OauthClientToken, Long> {

}
