package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(types = AuthorizationRequest.class)
public class AccessConfirmationController {

	private ClientDetailsService clientDetailsService;

	@Autowired
	public void setClientDetailsService(ClientDetailsService clientDetailsService) {
		this.clientDetailsService = clientDetailsService;
	}

	@RequestMapping("/oauth/confirm_access")
	public String getAccessConfirmation(@ModelAttribute AuthorizationRequest clientAuth, Model model) {

		ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
		model.addAttribute("auth_request", clientAuth);
		model.addAttribute("client", client);
		return "app.confirm";
	}
}
