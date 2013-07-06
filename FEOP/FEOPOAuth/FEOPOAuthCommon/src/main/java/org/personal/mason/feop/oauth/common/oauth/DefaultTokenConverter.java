package org.personal.mason.feop.oauth.common.oauth;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class DefaultTokenConverter implements AccessTokenConverter {

	public AppToken convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		AppToken appToken = new AppToken();
		AuthorizationRequest clientToken = authentication.getAuthorizationRequest();

		if (!authentication.isClientOnly()) {
			UserDetails principal = (UserDetails) authentication.getPrincipal();
			appToken.setUsername(principal.getUsername());
			appToken.setUserRoles(createRoleList(principal.getAuthorities()));
		}

		appToken.setScope(token.getScope());
		if (token.getExpiration() != null) {
			appToken.setExpire(token.getExpiration().getTime() / 1000);
		}

		appToken.setClientId(clientToken.getClientId());
		appToken.setClientRoles(createRoleList(clientToken.getAuthorities()));
		if (clientToken.getResourceIds() != null && !clientToken.getResourceIds().isEmpty()) {
			appToken.setAud(clientToken.getResourceIds());
		}
		return appToken;
	}

	private Set<String> createRoleList(Collection<? extends GrantedAuthority> authorities) {
		Set<String> roles = new HashSet<String>();
		for (GrantedAuthority authority : authorities) {
			roles.add(authority.getAuthority());
		}
		return roles;
	}

}
