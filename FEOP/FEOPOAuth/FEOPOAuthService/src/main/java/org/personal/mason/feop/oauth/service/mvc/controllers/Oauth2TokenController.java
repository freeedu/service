package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

//@Controller
public class Oauth2TokenController {

	private DefaultTokenServices tokenServices;
	
	@Autowired
	public void setTokenServices(DefaultTokenServices tokenServices) {
		this.tokenServices = tokenServices;
	}
	
	
}
