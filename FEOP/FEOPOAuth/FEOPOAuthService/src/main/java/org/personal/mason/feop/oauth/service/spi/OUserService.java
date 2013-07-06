package org.personal.mason.feop.oauth.service.spi;

import java.io.Serializable;
import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthUser;
import org.personal.mason.feop.oauth.service.mvc.model.SignupForm;

public interface OUserService {

	public void regist(OauthUser user);

	public OauthUser findUserById(Serializable id);

	public OauthUser findByEmailOrUsername(String emailOrUsername);

	public List<String> findUserRoles(OauthUser user);

	public OauthUser createUser(SignupForm signupForm);

	public void update(OauthUser user);

	public void updatePassword(OauthUser user, String password);
}
