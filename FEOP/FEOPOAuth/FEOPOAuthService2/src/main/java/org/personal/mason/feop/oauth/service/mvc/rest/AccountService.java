package org.personal.mason.feop.oauth.service.mvc.rest;

import org.personal.mason.feop.oauth.service.mvc.UserInfo;
import org.personal.mason.feop.oauth.service.mvc.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountService {

	private UserInfoService userInfoService;

	@Autowired
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	@RequestMapping(value = { "/account/verify_credentials" }, method = RequestMethod.GET)
	@ResponseBody
	public UserInfo validateCredentials() {
		return userInfoService.getCurrentUserInfo();
	}

}
