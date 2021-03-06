package org.personal.mason.feop.oauth.service.common.security;

import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FEOPLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        String redirectUri = request.getParameter("redirect_uri");
        if (redirectUri != null) {
            return redirectUri;
        }
        return super.determineTargetUrl(request, response);
    }

}
