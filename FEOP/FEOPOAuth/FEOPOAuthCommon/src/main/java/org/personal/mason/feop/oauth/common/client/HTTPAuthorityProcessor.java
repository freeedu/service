package org.personal.mason.feop.oauth.common.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.personal.mason.feop.oauth.common.client.oauth.FEOPAuthentication;
import org.personal.mason.feop.oauth.common.model.UserRole;
import org.personal.mason.feop.oauth.common.utils.Constrains;

public class HTTPAuthorityProcessor {
private final List<AuthorityInterceptor> interceptors;

public HTTPAuthorityProcessor(List<AuthorityInterceptor> interceptors) {
	this.interceptors = interceptors;
}

@Deprecated
public boolean needProcess(String uri) {
	if (interceptors != null) {
		for (AuthorityInterceptor interceptor : interceptors) {
			if (interceptor.needProcess(uri)) {
				return true;
			}
		}
	}
	return false;
}

private AuthorityInterceptor findProcessUrl(String uri) {
	if (interceptors != null) {
		for (int i = 0; i < interceptors.size(); i++) {
			if (interceptors.get(i).canProcess(uri)) {
				return interceptors.get(i);
			}
		}
	}
	return AuthorityInterceptor.defaultInterceptor;
}

public AuthenticationStatus checkStatus(HttpServletRequest request) {
	String requestURI = request.getRequestURI();
	AuthorityInterceptor processor = findProcessUrl(requestURI);
	if (processor != null) {
		if (processor.hasRoleControl()) {
			HttpSession session = request.getSession(true);
			FEOPAuthentication authentication = (FEOPAuthentication) session
					.getAttribute(Constrains.AUTHENTICATIOIN);
			if (authentication == null || !authentication.hasValidToken()) {
				return AuthenticationStatus.NotLogin;
			}
			
			if (checkRoles(authentication.getUserInfo().getRoles(), processor.getRequiredRoles())) {
				return AuthenticationStatus.Access;
			} else {
				return AuthenticationStatus.Denied;
			}
		} else {
			return AuthenticationStatus.Access;
		}
	}
	return AuthenticationStatus.Denied;
}

private static boolean checkRoles(List<UserRole> roles, List<String> checkRoles) {
	if (checkRoles == null || checkRoles.isEmpty()) {
		return true;
	}
	
	if (roles == null || roles.isEmpty()) {
		return false;
	}
	
	for (UserRole userRole : roles) {
		if (checkRoles.contains(userRole.getRoleName())) {
			return true;
		}
	}
	return false;
}
}
