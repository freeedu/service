package org.personal.mason.feop.oauth.contact.mvc.rest;

import org.personal.mason.feop.oauth.common.client.ClientConfiguration;
import org.personal.mason.feop.oauth.common.client.DBClientConfiguration;
import org.personal.mason.feop.oauth.common.client.TokenUtils;
import org.personal.mason.feop.oauth.common.client.oauth.FOEPAuthentication;
import org.personal.mason.feop.oauth.common.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/8/13
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/")
public class UserApi {


    private ClientConfiguration clientConfiguration;
    public TokenUtils tokenUtils;

    @Autowired
    public void setClientConfiguration(ClientConfiguration clientConfiguration){
        this.clientConfiguration = clientConfiguration;
    }
    @Autowired
    public void setTokenUtils(TokenUtils tokenUtils){
        this.tokenUtils = tokenUtils;
    }

    @RequestMapping(value = {"login"}, method = RequestMethod.GET)
    public UserInfo login(@RequestParam(required = false) String token) {

        if(token == null){
            return null;
        }

        FOEPAuthentication authentication = tokenUtils.getAuthentication(token);
        if (authentication == null) {
            return null;
        }

        return authentication.getUserInfo();
    }

    @RequestMapping(value = {"logout"})
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder logoutUri = new StringBuilder(clientConfiguration.getOauthLogoutUri());
        if (logoutUri.indexOf("?") > 0) {
            logoutUri.append("&redirect_uri=%s");
        } else {
            logoutUri.append("?redirect_uri=%s");
        }
        String registerUrl = String.format(logoutUri.toString(), getSiteRoot(request));

        String[] tokens = request.getParameterMap().get("token");
        if (tokens != null) {
            tokenUtils.removeAuthentication(tokens[0]);
        }
        request.removeAttribute("token");

        response.sendRedirect(registerUrl);
    }

    @RequestMapping(value = {"signup"}, method = RequestMethod.GET)
    public void signup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder registerUri = new StringBuilder(clientConfiguration.getOauthRegisterUri());
        if (registerUri.indexOf("?") > 0) {
            registerUri.append("&redirect_uri=%s");
        } else {
            registerUri.append("?redirect_uri=%s");
        }
        String registerUrl = String.format(registerUri.toString(), getSiteRoot(request));
        response.sendRedirect(registerUrl);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String home(@RequestParam(required = false) String token) {
        if (token == null) {
            return "REQUIRED_LOGIN";
        } else if (tokenUtils.validate(token)) {
            return "VALIDATED";
        } else {
            return "INVALIDATE";
        }
    }

    static String getSiteRoot(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        String requestUri = request.getRequestURI();
        return requestUrl.substring(0, requestUrl.lastIndexOf(requestUri));
    }
}
