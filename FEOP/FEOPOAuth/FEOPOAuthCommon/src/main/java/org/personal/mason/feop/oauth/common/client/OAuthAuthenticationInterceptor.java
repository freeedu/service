package org.personal.mason.feop.oauth.common.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                switch (foepLoginProcessor.checkLogin(request)){
                    case AUTHENTICATED:
                        return true;
                    case ACCESS_TOKEN:
                        foepLoginProcessor.accessTokenAndRedirect(request, response);
                        break;
                    case ACCESS_ERROR:
                        foepLoginProcessor.processAccessError(request, response);
                        break;
                    case REQUEST_AUTH:
                        foepLoginProcessor.authentication(request, response);
                        break;
                }
                break;
            default:
                response.sendRedirect(foepLoginProcessor.getErrorRedirectPage());
                break;
        }
        return false;
    }
}
