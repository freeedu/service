package org.personal.mason.feop.server.blog.mvc.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServerController {

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(Principal principal) {
		if (principal == null || principal.getName().isEmpty()) {
			return "redirect:/login";
		}
		return "app.homepage";
	}

	@RequestMapping(value = { "/", "index" }, method = RequestMethod.GET)
	public String index() {
		return "app.homepage";
	}

	@RequestMapping(value = { "errorPage" })
	public String error(@RequestParam(value = "error", required = false) String error, Model model) {
		model.addAttribute("error", error);
		return "app.error";
	}
}
