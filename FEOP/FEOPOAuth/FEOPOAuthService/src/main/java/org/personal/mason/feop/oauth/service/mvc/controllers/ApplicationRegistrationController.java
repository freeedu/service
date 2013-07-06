package org.personal.mason.feop.oauth.service.mvc.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.personal.mason.feop.oauth.service.domain.OauthClientDetail;
import org.personal.mason.feop.oauth.service.mvc.model.ClientForm;
import org.personal.mason.feop.oauth.service.mvc.model.UserApplication;
import org.personal.mason.feop.oauth.service.spi.OClientDetailService;
import org.personal.mason.feop.oauth.service.spi.impl.AuthorizationType;
import org.personal.mason.feop.oauth.service.utils.SecuriteGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ApplicationRegistrationController {

	private OClientDetailService clientDetailService;

	@Autowired
	public void setClientDetailService(OClientDetailService clientDetailService) {
		if (clientDetailService == null) {
			throw new IllegalArgumentException("client Details Service cannot be null");
		}
		this.clientDetailService = clientDetailService;
	}

	@RequestMapping(value = { "/client/", "/client/form" })
	public String signup(@ModelAttribute ClientForm clientForm, ModelMap map) {
		map.addAttribute("client_types", AuthorizationType.getAllTypes());
		return "client/form";
	}

	@RequestMapping(value = "/client/create", method = RequestMethod.POST)
	public String signup(@Valid ClientForm clientForm, BindingResult result, RedirectAttributes redirectAttributes, ModelMap map, Principal principal) {
		if (result.hasErrors()) {
			return "redirect:/client/form";
		}

		String appName = clientForm.getClientName();
		if (clientDetailService.findByClientId(appName) != null) {
			result.rejectValue("clientName", "errors.client.clientName", "Application Name already in use.");
			return "redirect:/client/form";
		}
		
		if(principal.getName() == null || principal.getName().isEmpty()){
			result.rejectValue("", "errors.client.clientName", "You do not have privilege for this action.");
			return "redirect:/client/form";
		}
		
		OauthClientDetail client = new OauthClientDetail();
		client.setClientId(appName);
		client.setClientSecret(SecuriteGenerator.generateUniqueSecret());
		client.setWebServerRedirectUri(clientForm.getRedirectUrl());
		client.setOwner(principal.getName());
		clientDetailService.decorateClientBy(client, clientForm.getClientType());

		clientDetailService.createApplication(client);
		map.addAttribute("client", client);
		return "client/info";
	}
	
	@RequestMapping(value={"/client/list"}, method=RequestMethod.GET)
	public String listClients(ModelMap model, Principal principal){
		String currentUser = principal.getName();
		
		List<OauthClientDetail> clients = clientDetailService.findAllOauthClientDetailsByUser(currentUser);
		List<UserApplication> apps = new ArrayList<>();
		
		for (OauthClientDetail detail : clients) {
			UserApplication app = new UserApplication();
			app.setId(detail.getId());
			app.setClientId(detail.getClientId());
			app.setClientSecret(detail.getClientSecret());
			app.setAuthorities(detail.getAuthorities());
			app.setAdditionalInfo(detail.getAdditionalInformation());
			app.setAuthorizedGrantTypes(detail.getAuthorizedGrantTypes());
			app.setResourceIds(detail.getResourceIds());
			app.setScope(detail.getScope());
			app.setWebServerRedirectUri(detail.getWebServerRedirectUri());
			apps.add(app);
		}
		
		model.addAttribute("applications", apps);
		return "client/list";
	}
	
	@RequestMapping(value={"/client/delete/{clientId}"})
	public String deleteApplication(@PathVariable("clientId") String clientId, Principal principal){
		OauthClientDetail client = clientDetailService.findByClientId(clientId);
		
		if(client != null && principal.getName() != null){
			clientDetailService.deleteApplication(client);
		}
		
		return "client/list";
	}
}
