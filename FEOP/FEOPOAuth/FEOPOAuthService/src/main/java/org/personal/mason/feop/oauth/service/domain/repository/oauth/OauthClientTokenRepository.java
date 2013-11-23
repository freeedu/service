package org.personal.mason.feop.oauth.service.domain.repository.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthClientToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthClientTokenRepository extends JpaRepository<OauthClientToken, Long> {

}
