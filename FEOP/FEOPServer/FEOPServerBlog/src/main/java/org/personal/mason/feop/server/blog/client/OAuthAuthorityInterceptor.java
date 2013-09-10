package org.personal.mason.feop.server.blog.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.personal.mason.feop.server.blog.client.oauth.OAuthLoginInfoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class OAuthAuthorityInterceptor extends HandlerInterceptorAdapter {

	private HTTPAuthorityProcessor processor;
	private OAuthLoginInfoProvider loginProcessor;

	@Autowired
	public void setProcessor(HTTPAuthorityProcessor processor) {
		this.processor = processor;
	}

	@Autowired
	public void setLoginProcessor(OAuthLoginInfoProvider loginProcessor) {
		this.loginProcessor = loginProcessor;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		switch (processor.checkStatus(request)) {
		case Access:
			return true;
		case Denied:
			response.sendRedirect("/errorPage?error=AccessDenied");
			break;
		case NotLogin:
			if (loginProcessor.isDirectlyRequestToken(request)) {
				loginProcessor.processAccessToken(request, response);
			} else {
				response.sendRedirect(loginProcessor.getAuthorizationRequestUrl(request));
			}
			break;
		default:
			response.sendRedirect("/errorPage?error=AccessDenied");
			break;
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
