package org.personal.mason.feop.oauth.service.mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OAuth2DefaultController {

	@RequestMapping("login")
	public String login() {
		return "app.login";
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		String redirectUri = request.getParameter("redirect_uri");

		if (redirectUri != null) {
			return String.format("redirect:%s", redirectUri);
		}
		return "app.logout";
	}

	@RequestMapping(value = { "/", "index" })
	public String index() {
		return "app.index";
	}

}
