package org.personal.mason.feop.oauth.service.repository;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthClientDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthClientDetailRepository extends JpaRepository<OauthClientDetail, Long> {

	List<OauthClientDetail> findByClientId(String clientid);

	List<OauthClientDetail> findByOwner(String owner);
}
