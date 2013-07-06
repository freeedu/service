package org.personal.mason.feop.oauth.account.spi.impl;

import java.util.Arrays;
import java.util.List;

import org.personal.mason.feop.oauth.account.domain.AccountUser;
import org.personal.mason.feop.oauth.account.domain.UserEmail;
import org.personal.mason.feop.oauth.account.repository.UserEmailRepository;
import org.personal.mason.feop.oauth.account.spi.UserEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserEmailServiceImpl implements UserEmailService {

	private UserEmailRepository userEmailRepository;

	@Autowired
	public void setUserEmailRepository(UserEmailRepository userEmailRepository) {
		this.userEmailRepository = userEmailRepository;
	}

	@Override
	@Transactional
	public UserEmail findDefaultEmail(AccountUser account) {
		List<UserEmail> defaultEmailes = userEmailRepository.findByAccountUserAndPrimary(account, true);
		return defaultEmailes.isEmpty() ? null : defaultEmailes.get(0);
	}

	@Override
	@Transactional
	public List<UserEmail> findAllEmails(AccountUser account) {
		return userEmailRepository.findByAccountUser(account);
	}

	@Override
	@Transactional
	public void saveEmail(UserEmail userEmail) {
		userEmailRepository.save(userEmail);
	}

	@Override
	@Transactional
	public UserEmail findEmailById(Long EmailId) {
		return userEmailRepository.findOne(EmailId);
	}

	@Override
	@Transactional
	public void deleteEmail(UserEmail userEmail) {
		userEmailRepository.delete(userEmail);
	}

	@Override
	@Transactional
	public UserEmail updateUserEmail(UserEmail userEmail) {
		return userEmailRepository.saveAndFlush(userEmail);
	}

	@Override
	@Transactional
	public boolean switchDefault(UserEmail defaultEmail, UserEmail newDefaultEmail) {
		try {
			defaultEmail.setPrimary(false);
			newDefaultEmail.setPrimary(true);
			userEmailRepository.save(Arrays.asList(defaultEmail, newDefaultEmail));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
