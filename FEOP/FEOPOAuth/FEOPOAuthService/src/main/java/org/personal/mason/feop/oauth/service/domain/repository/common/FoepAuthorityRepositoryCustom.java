package org.personal.mason.feop.oauth.service.domain.repository.common;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;

import java.util.List;

public interface FoepAuthorityRepositoryCustom {
    List<FoepAuthority> getDefaultUserRoles(Object... roleNames);
}
