package org.personal.mason.feop.oauth.service.spi.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.personal.mason.feop.oauth.service.domain.OauthRole;
import org.personal.mason.feop.oauth.service.domain.OauthUser;
import org.personal.mason.feop.oauth.service.domain.SystemSettings;
import org.personal.mason.feop.oauth.service.mvc.model.SignupForm;
import org.personal.mason.feop.oauth.service.repository.OauthRoleRepository;
import org.personal.mason.feop.oauth.service.repository.OauthUserRepository;
import org.personal.mason.feop.oauth.service.repository.SystemSettingsRepository;
import org.personal.mason.feop.oauth.service.spi.FeopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeopUserServiceImpl implements FeopUserService {
	private static final String DEFAULT_USER_ROLES = "default_user_roles";
	private OauthUserRepository oauthUserRepository;
	private OauthRoleRepository oauthRoleRepository;
	private SystemSettingsRepository systemSettingsRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setOauthUserRepository(OauthUserRepository oauthUserRepository) {
		this.oauthUserRepository = oauthUserRepository;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setOauthRoleRepository(OauthRoleRepository oauthRoleRepository) {
		this.oauthRoleRepository = oauthRoleRepository;
	}

	@Autowired
	public void setSystemSettingsRepository(SystemSettingsRepository systemSettingsRepository) {
		this.systemSettingsRepository = systemSettingsRepository;
	}

	@Override
	@Transactional
	public void update(OauthUser user) {
		oauthUserRepository.saveAndFlush(user);
	}

	@Transactional
	public void updatePassword(OauthUser user, String password) {
		String encodedPassword = passwordEncoder.encode(password);
		user.setPassword(encodedPassword);
		oauthUserRepository.saveAndFlush(user);
	}

	@Override
	@Transactional
	public void regist(OauthUser user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		List<SystemSettings> setting = systemSettingsRepository.findByKey(DEFAULT_USER_ROLES);

		if (setting != null && setting.size() > 0) {
			Object[] roleNames = setting.get(0).getValue().split(",[\\s]*");
			user.setRoles(oauthRoleRepository.getDefaultUserRoles(roleNames));
		} else {
			user.setRoles(oauthRoleRepository.getDefaultUserRoles());
		}
		oauthUserRepository.save(user);
	}

	@Override
	@Transactional
	public OauthUser findUserById(Long id) {
		return oauthUserRepository.findOne(id);
	}

	@Override
	@Transactional
	public OauthUser findByEmailOrUsername(String emailOrUsername) {
		List<OauthUser> apps = oauthUserRepository.findByUserNameOrEmail(emailOrUsername);
		return apps.isEmpty() ? null : apps.get(0);
	}

	@Override
	public List<String> findUserRoles(OauthUser user) {
		return splitRoleNames(user.getRoles());
	}

	private List<String> splitRoleNames(List<OauthRole> roles) {
		List<String> roleNames = new ArrayList<>();

		for (OauthRole role : roles) {
			if (role.getEnabled()) {
				roleNames.add(role.getName());
			}
		}
		return roleNames;
	}

	@Override
	public OauthUser createUser(SignupForm signupForm) {
		OauthUser user = new OauthUser();
		user.setActivated(true);
		user.setEmail(signupForm.getEmail());
		user.setPassword(signupForm.getPassword());
		user.setUserId(UUID.randomUUID().toString());
		user.setFirstName(signupForm.getFirstName());
		user.setLastName(signupForm.getLastName());
		if (signupForm.getUserName() == null) {
			user.setUserName(String.format("%s %s", signupForm.getFirstName(), signupForm.getLastName()).trim());
		} else {
			user.setUserName(signupForm.getUserName().trim());
		}
		user.setGender(signupForm.getGender());
		user.setLocation(signupForm.getLocation());
		user.setPhone(signupForm.getPhone());
		user.setProfileImageUri(signupForm.getProfileImageUri());

		return user;
	}

	@Override
	public boolean validate(String oldPassword, OauthUser ouser) {
		return passwordEncoder.matches(oldPassword, ouser.getPassword());
	}

}
