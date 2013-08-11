package org.personal.mason.feop.oauth.service.spi.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.personal.mason.feop.oauth.service.domain.PasswordReset;
import org.personal.mason.feop.oauth.service.repository.PasswordResetRepository;
import org.personal.mason.feop.oauth.service.spi.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

	private PasswordResetRepository passwordResetRepository;

	@Autowired
	public void setPasswordResetRepository(PasswordResetRepository passwordResetRepository) {
		this.passwordResetRepository = passwordResetRepository;
	}

	@Override
	@Transactional
	public void save(PasswordReset pwdReset) {
		passwordResetRepository.save(pwdReset);
	}

	@Override
	@Transactional
	public PasswordReset findByToken(String token) {
		List<PasswordReset> resets = passwordResetRepository.findByToken(token);
		return (resets != null && resets.size() > 0) ? resets.get(0) : null;
	}

	@Override
	@Transactional
	public void delete(PasswordReset... pwdReset) {
		passwordResetRepository.delete(Arrays.asList(pwdReset));
	}

	@Override
	@Transactional
	public List<PasswordReset> findByDateBefore(Date date) {
		return passwordResetRepository.findByRequestTimeBefore(date);
	}

}
