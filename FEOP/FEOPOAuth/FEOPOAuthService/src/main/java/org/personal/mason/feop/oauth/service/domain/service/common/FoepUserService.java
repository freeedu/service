package org.personal.mason.feop.oauth.service.domain.service.common;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepUser;
import org.personal.mason.feop.oauth.service.mvc.model.SignupForm;

import java.util.List;

public interface FoepUserService {

    public void regist(FoepUser user);

    public FoepUser findUserById(Long id);

    public FoepUser findByEmailOrUsername(String emailOrUsername);

    public FoepUser findByUserId(String userId);

    public List<String> findUserRoles(FoepUser user);

    public FoepUser createUser(SignupForm signupForm);

    public void update(FoepUser user);

    public void updatePassword(FoepUser user, String password);

    public boolean validate(String oldPassword, FoepUser ouser);


}
