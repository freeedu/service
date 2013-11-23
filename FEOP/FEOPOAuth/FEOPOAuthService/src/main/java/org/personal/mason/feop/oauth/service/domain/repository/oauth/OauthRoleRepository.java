package org.personal.mason.feop.oauth.service.domain.repository.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthRoleRepository extends JpaRepository<OauthRole, Long>, OauthRoleRepositoryCustom {

}
