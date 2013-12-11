package org.personal.mason.feop.oauth.service.domain.service.common.impl;

import org.personal.mason.feop.oauth.common.domain.model.SystemSetting;
import org.personal.mason.feop.oauth.common.domain.repository.SystemSettingRepository;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepUser;
import org.personal.mason.feop.oauth.service.domain.repository.common.FoepAuthorityRepository;
import org.personal.mason.feop.oauth.service.domain.repository.common.FoepUserRepository;
import org.personal.mason.feop.oauth.service.domain.service.common.FeopUserService;
import org.personal.mason.feop.oauth.service.mvc.model.SignupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class FeopUserServiceImpl implements FeopUserService {
    private static final String DEFAULT_USER_ROLES = "default_user_roles";
    private FoepUserRepository foepUserRepository;
    private FoepAuthorityRepository foepAuthorityRepository;
    private SystemSettingRepository systemSettingRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setFoepUserRepository(FoepUserRepository foepUserRepository) {
        this.foepUserRepository = foepUserRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setFoepAuthorityRepository(FoepAuthorityRepository foepAuthorityRepository) {
        this.foepAuthorityRepository = foepAuthorityRepository;
    }

    @Autowired
    public void setSystemSettingRepository(SystemSettingRepository systemSettingRepository) {
        this.systemSettingRepository = systemSettingRepository;
    }


    @Override
    @Transactional
    public void update(FoepUser user) {
        foepUserRepository.saveAndFlush(user);
    }

    @Transactional
    public void updatePassword(FoepUser user, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        foepUserRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void regist(FoepUser user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        List<SystemSetting> setting = systemSettingRepository.findByKey(DEFAULT_USER_ROLES);

        if (setting != null && setting.size() > 0) {
            Object[] roleNames = setting.get(0).getValue().split(",[\\s]*");
            List<FoepAuthority> defaultUserRoles = foepAuthorityRepository.getDefaultUserRoles(roleNames);
            user.setRoles(new HashSet<FoepAuthority>(defaultUserRoles));
        } else {
            List<FoepAuthority> defaultUserRoles = foepAuthorityRepository.getDefaultUserRoles();
            user.setRoles(new HashSet<FoepAuthority>(defaultUserRoles));
        }
        foepUserRepository.save(user);
    }

    @Override
    @Transactional
    public FoepUser findUserById(Long id) {
        return foepUserRepository.findOne(id);
    }

    @Override
    @Transactional
    public FoepUser findByEmailOrUsername(String emailOrUsername) {
        List<FoepUser> apps = foepUserRepository.findByUserNameOrEmail(emailOrUsername);
        return apps.isEmpty() ? null : apps.get(0);
    }

    @Override
    public List<String> findUserRoles(FoepUser user) {
        return splitRoleNames(user.getRoles());
    }

    private List<String> splitRoleNames(Set<FoepAuthority> roles) {
        List<String> roleNames = new ArrayList<>();

        for (FoepAuthority role : roles) {
            if (role.getEnabled()) {
                roleNames.add(role.getName());
            }
        }
        return roleNames;
    }

    @Override
    public FoepUser createUser(SignupForm signupForm) {
        FoepUser user = new FoepUser();
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEmail(signupForm.getEmail());
        user.setPassword(signupForm.getPassword());
        user.setUserId(UUID.randomUUID().toString());
        user.setFirstName(signupForm.getFirstName());
        user.setLastName(signupForm.getLastName());
        if (signupForm.getUserName() == null) {
            user.setUserName(String
                    .format("%s %s", signupForm.getFirstName(), signupForm.getLastName()).trim());
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
    public boolean validate(String oldPassword, FoepUser ouser) {
        return passwordEncoder.matches(oldPassword, ouser.getPassword());
    }

    @Override
    public FoepUser findByUserId(String userId) {
        List<FoepUser> users = foepUserRepository.findByUserId(userId);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

}
