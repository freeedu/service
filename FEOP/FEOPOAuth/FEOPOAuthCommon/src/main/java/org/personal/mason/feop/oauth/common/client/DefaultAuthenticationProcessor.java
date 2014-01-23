package org.personal.mason.feop.oauth.common.client;

import org.personal.mason.feop.oauth.common.client.oauth.FOEPAuthentication;
import org.personal.mason.feop.oauth.common.model.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class DefaultAuthenticationProcessor implements FOEPAuthenticationProcessor {
    private final List<AuthorityInterceptor> interceptors;
    private final TokenUtils tokenUtils;

    public DefaultAuthenticationProcessor(List<AuthorityInterceptor> interceptors, TokenUtils tokenUtils) {
        this.interceptors = interceptors;
        this.tokenUtils = tokenUtils;
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
        assert (tokenUtils != null);
        String requestURI = request.getRequestURI();
        AuthorityInterceptor processor = findProcessUrl(requestURI);
        if (processor != null) {
            if (!processor.hasRoleControl()) {
                return AuthenticationStatus.ALLOW_ACCESS;
            }

            FOEPAuthentication authentication = findAuthentication(request);

            if (authentication == null || !authentication.hasValidToken()) {
                return AuthenticationStatus.NOT_LOGIN;
            }

            if (checkRoles(authentication.getUserInfo().getRoles(), processor.getRequiredRoles())) {
                return AuthenticationStatus.ALLOW_ACCESS;
            } else {
                return AuthenticationStatus.DENIED;
            }
        }
        return AuthenticationStatus.DENIED;
    }

    private FOEPAuthentication findAuthentication(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object auth = session.getAttribute(FOEPAuthentication.SESSION_AUTHENTICATION);
            if (auth != null && auth instanceof FOEPAuthentication) {
                return (FOEPAuthentication) auth;
            }
        }

        Map<String, String[]> parms = request.getParameterMap();
        if (parms.containsKey("token")) {
            String token = parms.get("token")[0];
            return tokenUtils.getAuthentication(token);
        }

        String token = request.getHeader("token");
        if (token != null) {
            return tokenUtils.getAuthentication(token);
        }

        return null;
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
