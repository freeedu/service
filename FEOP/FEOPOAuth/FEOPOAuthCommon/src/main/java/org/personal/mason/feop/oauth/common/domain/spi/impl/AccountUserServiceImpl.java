package org.personal.mason.feop.oauth.common.domain.spi.impl;

import java.util.Date;
import java.util.List;

import org.personal.mason.feop.oauth.common.domain.model.AccountUser;
import org.personal.mason.feop.oauth.common.domain.repository.AccountUserRepository;
import org.personal.mason.feop.oauth.common.domain.spi.AccountUserService;
import org.personal.mason.feop.oauth.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountUserServiceImpl implements AccountUserService {

	private AccountUserRepository accountUserRepository;

	@Autowired
	public void setAccountUserRepository(AccountUserRepository accountUserRepository) {
		this.accountUserRepository = accountUserRepository;
	}

	@Transactional
	public AccountUser findUserByUserId(String userId) {
		List<AccountUser> accountUsers = accountUserRepository.findByUserId(userId);
		return accountUsers.isEmpty() ? null : accountUsers.get(0);
	}

	@Transactional
	public List<AccountUser> findUsersByBirth(Date date) {
		String birthMonthAndDay = DateUtils.getMonthAndDay(date);
		return accountUserRepository.findByBirthMonthDay(birthMonthAndDay);
	}

	@Transactional
	@Override
	public AccountUser findById(Long id) {
		return accountUserRepository.findOne(id);
	}

	@Transactional
	@Override
	public void createAccount(AccountUser accountUser) {
		accountUserRepository.save(accountUser);
	}

	@Transactional
	@Override
	public AccountUser updateAccount(AccountUser accountUser) {
		return accountUserRepository.saveAndFlush(accountUser);
	}

}
