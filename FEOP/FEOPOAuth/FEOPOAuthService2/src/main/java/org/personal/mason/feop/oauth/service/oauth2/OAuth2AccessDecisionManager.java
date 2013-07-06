package org.personal.mason.feop.oauth.service.oauth2;

import java.util.Collection;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

public class OAuth2AccessDecisionManager extends AbstractAccessDecisionManager {

	@Override
	public void decide(Authentication arg0, Object arg1, Collection<ConfigAttribute> arg2) throws AccessDeniedException,
			InsufficientAuthenticationException {
		// TODO Auto-generated method stub

	}

}
