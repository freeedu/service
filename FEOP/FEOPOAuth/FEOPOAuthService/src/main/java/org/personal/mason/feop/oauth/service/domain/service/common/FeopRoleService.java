package org.personal.mason.feop.oauth.service.domain.service.common;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;

import java.util.List;
import java.util.Set;

public interface FeopRoleService {

    public Set<FoepAuthority> getDefaultUserRoles();
}
