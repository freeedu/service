package org.personal.mason.feop.oauth.service.domain.service.common;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface FoepAuthorityService {

    public Set<FoepAuthority> getDefaultUserRoles();

    Set<FoepAuthority> findOrCreateAuthorities(Collection<? extends GrantedAuthority> authorities);

    FoepAuthority findByName(String authority);
}
