package org.personal.mason.feop.oauth.service.domain.repository.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthClientDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OauthClientDetailRepository extends JpaRepository<OauthClientDetail, Long> {

    List<OauthClientDetail> findByClientId(String clientid);

    List<OauthClientDetail> findByOwner(String owner);
}
