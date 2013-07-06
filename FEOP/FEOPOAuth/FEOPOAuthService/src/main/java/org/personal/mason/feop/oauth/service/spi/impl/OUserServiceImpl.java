package org.personal.mason.feop.oauth.service.spi.impl;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.personal.mason.feop.oauth.service.dao.OauthRoleDao;
import org.personal.mason.feop.oauth.service.dao.OauthUserDao;
import org.personal.mason.feop.oauth.service.domain.OauthUser;
import org.personal.mason.feop.oauth.service.mvc.model.SignupForm;
import org.personal.mason.feop.oauth.service.spi.OUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OUserServiceImpl implements OUserService {
	private OauthUserDao userDao;
	private OauthRoleDao roleDao;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setUserDao(OauthUserDao userDao) {
		if (userDao == null) {
			throw new IllegalArgumentException("user dao cannot be null");
		}
		this.userDao = userDao;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		if (passwordEncoder == null) {
			throw new IllegalArgumentException("password encoder cannot be null");
		}
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setRoleDao(OauthRoleDao roleDao) {
		if (roleDao == null) {
			throw new IllegalArgumentException("role Dao cannot be null");
		}
		this.roleDao = roleDao;
	}

	@Override
	@Transactional
	public void update(OauthUser user) {
		userDao.udpate(user);
	}
	
	@Transactional
	public void updatePassword(OauthUser user, String password) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userDao.udpate(user);
	}

	@Override
	@Transactional
	public void regist(OauthUser user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setRoles(roleDao.getDefaultUserRoles());
		userDao.saveObject(user);
	}

	@Override
	@Transactional
	public OauthUser findUserById(Serializable id) {
		return userDao.findById(id);
	}

	@Override
	@Transactional
	public OauthUser findByEmailOrUsername(String emailOrUsername) {
		return userDao.findByEmailOrUsername(emailOrUsername);
	}

	@Override
	public List<String> findUserRoles(OauthUser user) {
		return userDao.findUserRoles(user);
	}

	@Override
	public OauthUser createUser(SignupForm signupForm) {
		OauthUser user = new OauthUser();
		user.setActivated(true);
		user.setEmail(signupForm.getEmail());
		user.setPassword(signupForm.getPassword());
		user.setUserId(UUID.randomUUID().toString());
		user.setUserName(String.format("%s %s", signupForm.getFirstName(), signupForm.getLastName()).trim());
		return user;
	}

}
