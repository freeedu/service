package org.personal.mason.feop.oauth.service.domain.repository.common;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoepAuthorityRepository extends JpaRepository<FoepAuthority, Long>, FoepAuthorityRepositoryCustom {

}
