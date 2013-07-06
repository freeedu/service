package org.personal.mason.feop.oauth.service.repository;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthCodeRepository extends JpaRepository<OauthCode, Long> {
	List<OauthCode> findByCode(String code);
}
