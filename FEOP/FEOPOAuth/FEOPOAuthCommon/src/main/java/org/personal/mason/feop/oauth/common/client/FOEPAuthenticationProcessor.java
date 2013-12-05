package org.personal.mason.feop.oauth.common.client;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/3/13
 * Time: 12:49 AM
 * To change this template use File | Settings | File Templates.
 */
public interface FOEPAuthenticationProcessor {
    /**
     * Used to check the authentication status, include not_login, denied and allow_access
     * @param request
     * @return
     */
    public AuthenticationStatus checkStatus(HttpServletRequest request);
}
