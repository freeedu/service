package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthClientDetail;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopClientDetailService;
import org.personal.mason.feop.oauth.service.domain.service.oauth.impl.AuthorizationType;
import org.personal.mason.feop.oauth.service.mvc.model.ClientForm;
import org.personal.mason.feop.oauth.service.mvc.model.UserApplication;
import org.personal.mason.feop.oauth.service.utils.StringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationRegistrationController {

    private FeopClientDetailService feopClientDetailService;

    @Autowired
    public void setFeopClientDetailService(FeopClientDetailService feopClientDetailService) {
        this.feopClientDetailService = feopClientDetailService;
    }

    @RequestMapping(value = {"/client/new"})
    public String signup(@ModelAttribute ClientForm clientForm, ModelMap map) {
        map.addAttribute("client_types", AuthorizationType.getAllTypes());
        return "app.client.new";
    }

    @RequestMapping(value = "/client/save", method = RequestMethod.POST)
    public String signup(@Valid ClientForm clientForm, BindingResult result,  Principal principal, ModelMap map) {
        if (result.hasErrors()) {
            return "redirect:/client/new";
        }

        String appName = clientForm.getClientName();
        if (feopClientDetailService.findByClientId(appName) != null) {
            result.rejectValue("clientName", "errors.client.clientName", "Application Name already in use.");
            return "redirect:/client/new";
        }

        if (principal.getName() == null || principal.getName().isEmpty()) {
            result.rejectValue("", "errors.client.clientName", "You do not have privilege for this action.");
            return "redirect:/client/new";
        }

        OauthClientDetail client = new OauthClientDetail();
        client.setClientId(appName);
        client.setClientSecret(StringGenerator.generateUniqueSecret());
        client.setWebServerRedirectUri(clientForm.getRedirectUrl());
        client.setOwner(principal.getName());
        feopClientDetailService.decorateClientBy(client, clientForm.getClientType());

        feopClientDetailService.createApplication(client);
        map.addAttribute("client", client);
        return "app.client.view";
    }

    @RequestMapping(value = {"/client/list"}, method = RequestMethod.GET)
    public String listClients(ModelMap model, Principal principal) {
        String currentUser = principal.getName();

        List<OauthClientDetail> clients = feopClientDetailService.findAllOauthClientDetailsByUser(currentUser);
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
        return "app.client.list";
    }

    @RequestMapping(value = {"/client/delete/{clientId}"})
    public String deleteApplication(@PathVariable("clientId") String clientId, Principal principal) {
        OauthClientDetail client = feopClientDetailService.findByClientId(clientId);

        if (client != null && principal.getName() != null) {
            feopClientDetailService.deleteApplication(client);
        }

        return "redirect:/client/list";
    }

    @RequestMapping(value = {"/client/view/{clientId}"})
    public String viewApplication(@PathVariable("clientId") String clientId, ModelMap map, Principal principal) {
        OauthClientDetail client = feopClientDetailService.findByClientId(clientId);

        if (client != null && principal.getName() != null) {
            map.addAttribute("client", client);
        }

        return "app.client.view";
    }
}
