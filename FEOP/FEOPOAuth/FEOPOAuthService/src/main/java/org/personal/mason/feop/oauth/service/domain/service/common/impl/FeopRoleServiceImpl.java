package org.personal.mason.feop.oauth.service.domain.service.common.impl;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;
import org.personal.mason.feop.oauth.service.domain.repository.common.FoepAuthorityRepository;
import org.personal.mason.feop.oauth.service.domain.service.common.FeopRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FeopRoleServiceImpl implements FeopRoleService {

    private FoepAuthorityRepository foepAuthorityRepository;

    @Autowired
    public void setFoepAuthorityRepository(FoepAuthorityRepository foepAuthorityRepository) {
        this.foepAuthorityRepository = foepAuthorityRepository;
    }

    @Override
    public Set<FoepAuthority> getDefaultUserRoles() {
        List<FoepAuthority> defaultUserRoles = foepAuthorityRepository.getDefaultUserRoles();
        if(defaultUserRoles == null || defaultUserRoles.isEmpty()){
            return Collections.emptySet();
        }
        return new HashSet<>(defaultUserRoles);
    }

}
