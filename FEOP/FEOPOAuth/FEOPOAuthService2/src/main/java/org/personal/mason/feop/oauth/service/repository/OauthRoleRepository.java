package org.personal.mason.feop.oauth.service.repository;

import org.personal.mason.feop.oauth.service.domain.OauthRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthRoleRepository extends JpaRepository<OauthRole, Long>, OauthRoleRepositoryCustom {

}
