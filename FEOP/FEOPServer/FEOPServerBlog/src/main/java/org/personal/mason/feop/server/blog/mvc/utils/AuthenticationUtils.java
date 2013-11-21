package org.personal.mason.feop.server.blog.mvc.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.personal.mason.feop.oauth.common.client.oauth.FEOPAuthentication;
import org.personal.mason.feop.server.blog.utils.Constrains;

public class AuthenticationUtils {

	public static String getUserName(HttpServletRequest request) {
		FEOPAuthentication authentication = getAuthentication(request);
		if (authentication != null) {
			return authentication.getUserInfo().getScreenName();
		}
		return null;
	}

	private static FEOPAuthentication getAuthentication(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		FEOPAuthentication authentication = (FEOPAuthentication) session.getAttribute(Constrains.AUTHENTICATIOIN);
		return authentication;
	}

	public static String getUid(HttpServletRequest request) {
		FEOPAuthentication authentication = getAuthentication(request);
		if (authentication != null) {
			return authentication.getUserInfo().getUserId();
		}
		return null;
	}

	public static String getUserEmail(HttpServletRequest request) {
		FEOPAuthentication authentication = getAuthentication(request);
		if (authentication != null) {
			return authentication.getUserInfo().getEmail();
		}
		return null;
	}
}
