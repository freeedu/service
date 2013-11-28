package org.personal.mason.feop.oauth.service.domain.service.oauth.impl;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthRole;
import org.personal.mason.feop.oauth.service.domain.repository.oauth.OauthRoleRepository;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeopRoleServiceImpl implements FeopRoleService {

    private OauthRoleRepository oauthRoleRepository;

    @Autowired
    public void setOauthRoleRepository(OauthRoleRepository oauthRoleRepository) {
        this.oauthRoleRepository = oauthRoleRepository;
    }

    @Override
    public List<OauthRole> getDefaultUserRoles() {
        return oauthRoleRepository.getDefaultUserRoles();
    }

}
