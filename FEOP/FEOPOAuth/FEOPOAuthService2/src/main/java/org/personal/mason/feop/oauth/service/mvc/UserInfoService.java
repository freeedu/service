package org.personal.mason.feop.oauth.service.mvc;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserInfoService {
	public UserInfo getCurrentUserInfo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(name);
		return userInfo;
	}

}
