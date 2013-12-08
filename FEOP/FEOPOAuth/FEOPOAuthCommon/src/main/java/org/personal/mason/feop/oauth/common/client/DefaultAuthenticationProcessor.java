package org.personal.mason.feop.oauth.common.client;

import org.personal.mason.feop.oauth.common.client.oauth.FOEPAuthentication;
import org.personal.mason.feop.oauth.common.model.UserRole;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class DefaultAuthenticationProcessor implements FOEPAuthenticationProcessor {
    private final List<AuthorityInterceptor> interceptors;
    private TokenUtils tokenUtils;

    public DefaultAuthenticationProcessor(List<AuthorityInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public void setTokenUtils(TokenUtils tokenUtils) {
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
            Map<String, String[]> parms = request.getParameterMap();
            if(!parms.containsKey("token")) {
                return AuthenticationStatus.NOT_LOGIN;
            }

            String token = parms.get("token")[0];

            FOEPAuthentication authentication = tokenUtils.getAuthentication(token);

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
