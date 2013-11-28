package org.personal.mason.feop.oauth.service.domain.repository.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OauthCodeRepository extends JpaRepository<OauthCode, Long> {
    List<OauthCode> findByCode(String code);
}
