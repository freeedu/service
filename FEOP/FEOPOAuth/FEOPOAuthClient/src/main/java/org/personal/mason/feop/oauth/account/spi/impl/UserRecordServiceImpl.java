package org.personal.mason.feop.oauth.account.spi.impl;

import java.util.List;

import org.personal.mason.feop.oauth.account.domain.AccountUser;
import org.personal.mason.feop.oauth.account.domain.UserRecord;
import org.personal.mason.feop.oauth.account.repository.UserRecordRepository;
import org.personal.mason.feop.oauth.account.spi.UserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserRecordServiceImpl implements UserRecordService {
	private UserRecordRepository userRecordRepository;

	@Autowired
	public void setRecordRepository(UserRecordRepository recordRepository) {
		this.userRecordRepository = recordRepository;
	}

	@Override
	@Transactional
	public List<UserRecord> findAllRecords(AccountUser account) {
		return userRecordRepository.findByAccountUser(account);
	}

	@Override
	@Transactional
	public void saveRecord(UserRecord userRecord) {
		userRecordRepository.save(userRecord);
	}

	@Override
	@Transactional
	public UserRecord findRecordById(Long recordId) {
		return userRecordRepository.findOne(recordId);
	}

	@Override
	@Transactional
	public void deleteRecord(UserRecord record) {
		userRecordRepository.delete(record);
	}

	@Override
	@Transactional
	public UserRecord updateUserRecord(UserRecord record) {
		return userRecordRepository.saveAndFlush(record);
	}
}
