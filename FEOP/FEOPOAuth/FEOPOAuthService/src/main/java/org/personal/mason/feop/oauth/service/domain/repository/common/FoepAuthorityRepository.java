package org.personal.mason.feop.oauth.service.domain.repository.common;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoepAuthorityRepository extends JpaRepository<FoepAuthority, Long>, FoepAuthorityRepositoryCustom {

    List<FoepAuthority> findByName(String name);
}
