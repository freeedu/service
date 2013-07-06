package org.personal.mason.feop.oauth.account.spi.impl;

import java.util.Arrays;
import java.util.List;

import org.personal.mason.feop.oauth.account.domain.AccountUser;
import org.personal.mason.feop.oauth.account.domain.UserPhone;
import org.personal.mason.feop.oauth.account.repository.UserPhoneRepository;
import org.personal.mason.feop.oauth.account.spi.UserPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserPhoneServiceImpl implements UserPhoneService {
	private UserPhoneRepository userPhoneRepository;

	@Autowired
	public void setUserPhoneRepository(UserPhoneRepository userPhoneRepository) {
		this.userPhoneRepository = userPhoneRepository;
	}

	@Override
	@Transactional
	public UserPhone findDefaultPhone(AccountUser account) {
		List<UserPhone> defaultPhonees = userPhoneRepository.findByAccountUserAndPrimary(account, true);
		return defaultPhonees.isEmpty() ? null : defaultPhonees.get(0);
	}

	@Override
	@Transactional
	public List<UserPhone> findAllPhones(AccountUser account) {
		return userPhoneRepository.findByAccountUser(account);
	}

	@Override
	@Transactional
	public void savePhone(UserPhone userPhone) {
		userPhoneRepository.save(userPhone);
	}

	@Override
	@Transactional
	public UserPhone findPhoneById(Long PhoneId) {
		return userPhoneRepository.findOne(PhoneId);
	}

	@Override
	@Transactional
	public void deletePhone(UserPhone userPhone) {
		userPhoneRepository.delete(userPhone);
	}

	@Override
	@Transactional
	public UserPhone updateUserPhone(UserPhone userPhone) {
		return userPhoneRepository.saveAndFlush(userPhone);
	}

	@Override
	@Transactional
	public boolean switchDefault(UserPhone defaultPhone, UserPhone newDefaultPhone) {
		try {
			defaultPhone.setPrimary(false);
			newDefaultPhone.setPrimary(true);
			userPhoneRepository.save(Arrays.asList(defaultPhone, newDefaultPhone));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
