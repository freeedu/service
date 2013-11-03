package org.personal.mason.feop.oauth.common.domain.spi.impl;

import java.util.Arrays;
import java.util.List;

import org.personal.mason.feop.oauth.common.domain.model.AccountUser;
import org.personal.mason.feop.oauth.common.domain.model.UserIM;
import org.personal.mason.feop.oauth.common.domain.repository.UserIMRepository;
import org.personal.mason.feop.oauth.common.domain.spi.UserIMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserIMServiceImpl implements UserIMService {

	private UserIMRepository userIMRepository;

	@Autowired
	public void setUserIMRepository(UserIMRepository userIMRepository) {
		this.userIMRepository = userIMRepository;
	}

	@Override
	@Transactional
	public UserIM findDefaultIM(AccountUser account) {
		List<UserIM> defaultIMes = userIMRepository.findByAccountUserAndPrimary(account, true);
		return defaultIMes.isEmpty() ? null : defaultIMes.get(0);
	}

	@Override
	@Transactional
	public List<UserIM> findAllIMs(AccountUser account) {
		return userIMRepository.findByAccountUser(account);
	}

	@Override
	@Transactional
	public void saveIM(UserIM userIM) {
		userIMRepository.save(userIM);
	}

	@Override
	@Transactional
	public UserIM findIMById(Long IMId) {
		return userIMRepository.findOne(IMId);
	}

	@Override
	@Transactional
	public void deleteIM(UserIM userIM) {
		userIMRepository.delete(userIM);
	}

	@Override
	@Transactional
	public UserIM updateUserIM(UserIM userIM) {
		return userIMRepository.saveAndFlush(userIM);
	}

	@Override
	@Transactional
	public boolean switchDefault(UserIM defaultIM, UserIM newDefaultIM) {
		try {
			defaultIM.setPrimary(false);
			newDefaultIM.setPrimary(true);
			userIMRepository.save(Arrays.asList(defaultIM, newDefaultIM));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
