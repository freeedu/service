package org.personal.mason.feop.oauth.service.mvc.controllers;

import java.security.Principal;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Oauth2TokenController {

	@RequestMapping(value = { "/me/info" })
	public String checkToken(Principal principal) {
		if (principal instanceof OAuth2Authentication) {
			OAuth2Authentication authentication = (OAuth2Authentication) principal;
			return authentication.getName();
		}

		return principal.getName();
	}

}
