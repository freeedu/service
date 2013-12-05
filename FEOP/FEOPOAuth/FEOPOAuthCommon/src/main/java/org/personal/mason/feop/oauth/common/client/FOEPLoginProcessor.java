package org.personal.mason.feop.oauth.common.client;

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

    LoginStatus checkLogin(HttpServletRequest request);

    void accessTokenAndRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void authentication(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void retrieveUserInfo(HttpServletRequest request) throws IOException;

    void processAccessError(HttpServletRequest request, HttpServletResponse response) throws IOException;

    String getErrorRedirectPage();
}
