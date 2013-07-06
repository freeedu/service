package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OAuth2DefaultController {

	@RequestMapping("login")
	public String login() {
		return "login";
	}

	@RequestMapping("logout")
	public String logout() {
		return "logout";
	}

	@RequestMapping(value = { "/", "index" })
	public String index() {
		return "index";
	}

}
