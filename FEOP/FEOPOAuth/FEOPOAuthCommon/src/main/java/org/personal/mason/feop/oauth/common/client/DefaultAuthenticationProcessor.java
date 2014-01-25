package org.personal.mason.feop.oauth.common.client;

import org.personal.mason.feop.oauth.common.client.oauth.FOEPAuthentication;
import org.personal.mason.feop.oauth.common.model.UserRole;

import javax.servlet.http.*;
import java.util.List;
import java.util.Map;

public class DefaultAuthenticationProcessor implements FOEPAuthenticationProcessor {
    private final List<AuthorityInterceptor> interceptors;
    private final TokenUtils tokenUtils;
    private final HoldAuthenticationType holdAuthenticationType;

    public DefaultAuthenticationProcessor(List<AuthorityInterceptor> interceptors, TokenUtils tokenUtils) {
        this(interceptors, tokenUtils, HoldAuthenticationType.IN_SESSION);
    }

    public DefaultAuthenticationProcessor(List<AuthorityInterceptor> interceptors, TokenUtils tokenUtils, HoldAuthenticationType holdAuthenticationType) {
        this.interceptors = interceptors;
        this.tokenUtils = tokenUtils;
        this.holdAuthenticationType = holdAuthenticationType;
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

    @Override
    public AuthenticationStatus checkStatus(HttpServletRequest request) {
        assert (tokenUtils != null);
        String requestURI = request.getRequestURI();
        AuthorityInterceptor processor = findProcessUrl(requestURI);
        if (processor != null) {
            if (!processor.hasRoleControl()) {
                return AuthenticationStatus.ALLOW_ACCESS;
            }

            FOEPAuthentication authentication = findAuthentication(request);

            if (authentication == null){
                return AuthenticationStatus.NOT_LOGIN;
            }

            if(!authentication.hasValidToken()) {
                if(authentication.isTokenExpired()){
                    return AuthenticationStatus.EXPIRED;
                }

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

    public FOEPAuthentication findAuthentication(HttpServletRequest request) {
        switch (holdAuthenticationType){
            case IN_SESSION:{
                HttpSession session = request.getSession(false);

                if (session == null) {
                    return null;
                }

                Object auth = session.getAttribute(FOEPAuthentication.SESSION_AUTHENTICATION);
                if (auth != null && auth instanceof FOEPAuthentication) {
                    return (FOEPAuthentication) auth;
                }

                return null;
            }
            case IN_COOKIES:{
                Cookie[] cookies = request.getCookies();
                if(cookies == null){
                    return null;
                }

                for(Cookie cookie : cookies){
                    if(cookie.getName().equalsIgnoreCase(FOEPAuthentication.COOKIE_AUTHENTICATION)){
                        String token = cookie.getValue();
                        return tokenUtils.getAuthentication(token);
                    }
                }
                return null;
            }
            case IN_HEADER:{
                String token = request.getHeader(FOEPAuthentication.HEADER_AUTHENTICATION);
                if(token != null){
                    return tokenUtils.getAuthentication(token);
                }
                return null;
            }

        }
        return null;
    }

    @Override
    public void updateRequest(HttpServletRequest request, HttpServletResponse response, FOEPAuthentication authentication) {
        switch (holdAuthenticationType){
            case IN_SESSION: {
                HttpSession session = request.getSession(true);
                session.setAttribute(FOEPAuthentication.SESSION_AUTHENTICATION, authentication);
                break;
            }
            case IN_COOKIES:{
                Cookie cookie = new Cookie(FOEPAuthentication.COOKIE_AUTHENTICATION, authentication.getAccessToken());
                response.addCookie(cookie);
                break;
            }
            case IN_HEADER:{
                response.setHeader(FOEPAuthentication.HEADER_AUTHENTICATION, authentication.getAccessToken());
                break;
            }
            default:{
                HttpSession session = request.getSession(true);
                session.setAttribute(FOEPAuthentication.SESSION_AUTHENTICATION, authentication);
                break;
            }
        }
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
