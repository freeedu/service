package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.personal.mason.feop.oauth.common.spi.SettingsHolder;
import org.personal.mason.feop.oauth.service.utils.SettingKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OAuth2DefaultController {

    private SettingsHolder settingsHolder;

    @Autowired
    public void setSettingsHolder(SettingsHolder settingsHolder) {
        this.settingsHolder = settingsHolder;
    }

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public String login() {
        return "app.login";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        String redirectUri = request.getParameter("redirect_uri");

        if (redirectUri != null) {
            return String.format("redirect:%s", redirectUri);
        }
        return "app.logout";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index() {
        if(settingsHolder.findWithKey(SettingKeys.REDIRECT_LOGIN) != null){
            return "redirect:/login";
        }

        return "app.index";
    }

}
