package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.personal.mason.feop.oauth.common.oauth.AccessTokenConverter;
import org.personal.mason.feop.oauth.common.oauth.AppToken;
import org.personal.mason.feop.oauth.common.oauth.DefaultTokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Oauth2TokenController {
	private AccessTokenConverter tokenConverter = new DefaultTokenConverter();

	private DefaultTokenServices defaultTokenServices;

	@Autowired
	public void setDefaultTokenServices(DefaultTokenServices defaultTokenServices) {
		this.defaultTokenServices = defaultTokenServices;
	}

	public AppToken checkToken(@PathVariable String token) {
		OAuth2AccessToken accessToken = defaultTokenServices.readAccessToken(token);
		if (accessToken == null) {
			throw new InvalidTokenException("Token wat not recognized");
		}

		if (accessToken.isExpired()) {
			throw new InvalidTokenException("Token has expired");
		}

		OAuth2Authentication auth2Authentication = defaultTokenServices.loadAuthentication(token);
		return tokenConverter.convertAccessToken(accessToken, auth2Authentication);
	}

}
