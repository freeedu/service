package org.personal.mason.feop.oauth.common.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuthAuthenticationInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(OAuthAuthenticationInterceptor.class);

    private FOEPAuthenticationProcessor foepAuthenticationProcessor;
    private FOEPLoginProcessor foepLoginProcessor;

    public void setFoepAuthenticationProcessor(FOEPAuthenticationProcessor foepAuthenticationProcessor) {
        this.foepAuthenticationProcessor = foepAuthenticationProcessor;
    }

    public void setFoepLoginProcessor(FOEPLoginProcessor foepLoginProcessor) {
        this.foepLoginProcessor = foepLoginProcessor;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("start check authentication information");
        switch (foepAuthenticationProcessor.checkStatus(request)) {
            case ALLOW_ACCESS:
                return true;
            case DENIED:
                response.sendRedirect(foepLoginProcessor.getErrorRedirectPage());
                break;
            case NOT_LOGIN:
                processLogin(request, response, handler);
                break;
            default:
                response.sendRedirect(foepLoginProcessor.getErrorRedirectPage());
                break;
        }
        return false;
    }

    private void processLogin(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        switch (foepLoginProcessor.checkLogin(request)){
            case REDIRECT_LOGIN:
                foepLoginProcessor.redirectToLogin(request, response);
                break;
            case REQUEST_AUTH:
                foepLoginProcessor.authentication(request, response);
                break;
            case ACCESS_TOKEN:
                foepLoginProcessor.accessTokenAndRedirect(request, response);
                break;
            case ACCESS_ERROR:
                foepLoginProcessor.processAccessError(request, response);
                break;
        }
    }
}
