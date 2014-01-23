package org.personal.mason.feop.server.blog.mvc.utils;

import org.personal.mason.feop.oauth.common.client.oauth.FOEPAuthentication;
import org.personal.mason.feop.server.blog.utils.Constrains;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthenticationUtils {

    public static String getUserName(HttpServletRequest request) {
        FOEPAuthentication authentication = getAuthentication(request);
        if (authentication != null) {
            return authentication.getUserInfo().getScreenName();
        }
        return null;
    }

    private static FOEPAuthentication getAuthentication(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        FOEPAuthentication authentication = (FOEPAuthentication) session.getAttribute(FOEPAuthentication.SESSION_AUTHENTICATION);
        return authentication;
    }

    public static String getUid(HttpServletRequest request) {
        FOEPAuthentication authentication = getAuthentication(request);
        if (authentication != null) {
            return authentication.getUserInfo().getUserId();
        }
        return null;
    }

    public static String getUserEmail(HttpServletRequest request) {
        FOEPAuthentication authentication = getAuthentication(request);
        if (authentication != null) {
            return authentication.getUserInfo().getEmail();
        }
        return null;
    }
}
