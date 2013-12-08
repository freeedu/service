package org.personal.mason.feop.oauth.common.client;

import org.personal.mason.feop.oauth.common.client.oauth.FOEPAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/3/13
 * Time: 1:02 AM
 * To change this template use File | Settings | File Templates.
 */
public interface FOEPLoginProcessor {

    void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException;

    LoginStatus checkLogin(HttpServletRequest request);

    void accessTokenAndRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void authentication(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void retrieveUserInfo(String accessToken) throws IOException;

    void processAccessError(HttpServletRequest request, HttpServletResponse response) throws IOException;

    String getErrorRedirectPage();

    void redirectToSuccessPage(HttpServletRequest request, HttpServletResponse response, FOEPAuthentication authentication) throws IOException;
}
