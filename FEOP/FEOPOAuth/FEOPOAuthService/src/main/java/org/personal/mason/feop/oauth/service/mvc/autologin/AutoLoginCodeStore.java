package org.personal.mason.feop.oauth.service.mvc.autologin;

import org.springframework.security.core.Authentication;

public interface AutoLoginCodeStore {

	public Authentication getUser(String code);

	public String storeUser(Authentication authentication);

}
