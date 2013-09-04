package org.personal.mason.feop.server.blog.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.personal.mason.feop.server.blog.client.ClientConfiguration;
import org.personal.mason.feop.server.blog.client.oauth.FEOPAuthentication;
import org.personal.mason.feop.server.blog.utils.Constrains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	private ClientConfiguration clientConfiguration;

	@Autowired
	public void setClientConfiguration(ClientConfiguration clientConfiguration) {
		this.clientConfiguration = clientConfiguration;
	}

	@RequestMapping(value = { "/user/login" }, method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		FEOPAuthentication authentication = (FEOPAuthentication) request.getAttribute(Constrains.AUTHENTICATIOIN);
		authentication.getUserInfo();
		return "app.homepage";
	}

	@RequestMapping(value = { "/user/logout" })
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		StringBuilder logoutUri = new StringBuilder(clientConfiguration.getOauthLogoutUri());
		if (logoutUri.indexOf("?") > 0) {
			logoutUri.append("&redirect_uri=%s");
		} else {
			logoutUri.append("?redirect_uri=%s");
		}
		String registerUrl = String.format(logoutUri.toString(), getSiteRoot(request));
		request.getSession().removeAttribute(Constrains.AUTHENTICATIOIN);
		return String.format("redirect:%s", registerUrl);
	}

	@RequestMapping(value = { "/signup" })
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
