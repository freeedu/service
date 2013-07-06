package org.personal.mason.feop.oauth.service.spi.impl;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthRole;
import org.personal.mason.feop.oauth.service.repository.OauthRoleRepository;
import org.personal.mason.feop.oauth.service.spi.FeopRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
