package org.personal.mason.feop.oauth.service.mvc.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("authorizationRequest")
public class AccessConfirmationController {

	private ClientDetailsService clientDetailsService;

	@Autowired
	public void setClientDetailsService(ClientDetailsService clientDetailsService) {
		this.clientDetailsService = clientDetailsService;
	}

	@RequestMapping("/oauth/confirm_access")
	public String getAccessConfirmation(Map<String, Object> map) {
		AuthorizationRequest clientAuth = (AuthorizationRequest) map.remove("authorizationRequest");

		ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
		map.put("auth_request", clientAuth);
		map.put("client", client);
		return "app.confirm";
	}

	@RequestMapping("/oauth/error")
	public String handleError(Map<String, Object> model) throws Exception {
		// We can add more stuff to the model here for JSP rendering. If the
		// client was a machine then
		// the JSON will already have been rendered.
		model.put("message", "There was a problem with the OAuth2 protocol");
		return "oauth2/oauth_error";
	}
}
