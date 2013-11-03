package org.personal.mason.feop.oauth.common.domain.spi.impl;

import java.util.List;

import org.personal.mason.feop.oauth.common.domain.model.AccountUser;
import org.personal.mason.feop.oauth.common.domain.model.UserResource;
import org.personal.mason.feop.oauth.common.domain.repository.UserResourceRepository;
import org.personal.mason.feop.oauth.common.domain.spi.UserResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserResourceServiceImpl implements UserResourceService {

	private UserResourceRepository userResourceRepository;

	@Autowired
	public void setUserResourceRepository(UserResourceRepository userResourceRepository) {
		this.userResourceRepository = userResourceRepository;
	}

	@Override
	@Transactional
	public List<UserResource> findAllResources(AccountUser account) {
		return userResourceRepository.findByAccountUser(account);
	}

	@Override
	@Transactional
	public void saveResource(UserResource userResource) {
		userResourceRepository.save(userResource);
	}

	@Override
	@Transactional
	public UserResource findResourceById(Long resourceId) {
		return userResourceRepository.findOne(resourceId);
	}

	@Override
	@Transactional
	public void deleteResource(UserResource resource) {
		userResourceRepository.delete(resource);
	}

	@Override
	@Transactional
	public UserResource updateUserResource(UserResource resource) {
		return userResourceRepository.saveAndFlush(resource);
	}
}