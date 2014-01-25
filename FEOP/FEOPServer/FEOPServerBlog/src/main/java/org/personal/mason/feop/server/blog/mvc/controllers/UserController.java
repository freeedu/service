package org.personal.mason.feop.server.blog.mvc.controllers;

import org.personal.mason.feop.oauth.common.client.ClientConfiguration;
import org.personal.mason.feop.oauth.common.client.TokenUtils;
import org.personal.mason.feop.oauth.common.client.oauth.FOEPAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private ClientConfiguration clientConfiguration;

    @Autowired
    public TokenUtils tokenUtils;

    @Autowired
    public void setClientConfiguration(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = clientConfiguration;
    }

    @RequestMapping(value = {"/user/login"}, method = RequestMethod.GET)
    public String login(@RequestParam(required = true) String token, HttpServletRequest request) {
        if (token != null) {
            FOEPAuthentication authentication = tokenUtils.getAuthentication(token);
            if (authentication != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute(FOEPAuthentication.SESSION_AUTHENTICATION, authentication);
            }
        }

        return "app.homepage";
    }

    @RequestMapping(value = {"/user/logout"})
    public String logout(HttpServletRequest request) {
        StringBuilder logoutUri = new StringBuilder(clientConfiguration.getOauthLogoutUri());
        if (logoutUri.indexOf("?") > 0) {
            logoutUri.append("&redirect_uri=%s");
        } else {
            logoutUri.append("?redirect_uri=%s");
        }
        String logoutUrl = String.format(logoutUri.toString(), getSiteRoot(request));

        String[] tokens = request.getParameterMap().get("token");
        if (tokens != null) {
            tokenUtils.removeAuthentication(tokens[0]);
        }
        HttpSession session = request.getSession();
        session.removeAttribute(FOEPAuthentication.SESSION_AUTHENTICATION);
        request.removeAttribute("token");

        return String.format("redirect:%s", logoutUrl);
    }

    @RequestMapping(value = {"/signup"})
    public String signup(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder registerUri = new StringBuilder(clientConfiguration.getOauthRegisterUri());
        if (registerUri.indexOf("?") > 0) {
            registerUri.append("&redirect_uri=%s");
        } else {
            registerUri.append("?redirect_uri=%s");
        }
        String registerUrl = String.format(registerUri.toString(), getSiteRoot(request));
        return String.format("redirect:%s", registerUrl);
    }

    static String getSiteRoot(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        String requestUri = request.getRequestURI();
        return requestUrl.substring(0, requestUrl.lastIndexOf(requestUri));
    }

}
