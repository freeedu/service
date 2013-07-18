package org.personal.mason.feop.server.blog.mvc.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ServerController {

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(Principal principal) {
		if (principal == null || principal.getName().isEmpty()) {
			return "redirect:/login";
		}
		return "home";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		return "logout";
	}

	@RequestMapping(value = { "/", "index" }, method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
