package org.personal.mason.feop.oauth.service.domain.service.common.impl;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;
import org.personal.mason.feop.oauth.service.domain.repository.common.FoepAuthorityRepository;
import org.personal.mason.feop.oauth.service.domain.service.common.FoepAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FoepAuthorityServiceImpl implements FoepAuthorityService {

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

    @Override
    public Set<FoepAuthority> findOrCreateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<FoepAuthority> foepAuthoritySet = new HashSet<>();
        if(authorities != null){
            for (GrantedAuthority authority : authorities){
                FoepAuthority foepAuthority = findByName(authority.getAuthority());
                if(foepAuthority == null){
                    foepAuthority = new FoepAuthority();
                    foepAuthority.setEnabled(true);
                    foepAuthority.setName(authority.getAuthority());
                    foepAuthorityRepository.saveAndFlush(foepAuthority);
                }
                foepAuthoritySet.add(foepAuthority);
            }
        }
        return foepAuthoritySet;
    }

    @Override
    public FoepAuthority findByName(String authority) {
        List<FoepAuthority> authorities = foepAuthorityRepository.findByName(authority);
        if(authorities != null && !authorities.isEmpty()) {
            return authorities.get(0);
        }
        return null;
    }

}
