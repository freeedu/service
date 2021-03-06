package org.personal.mason.feop.oauth.common.client;

import org.personal.mason.feop.oauth.common.client.oauth.FOEPAuthentication;
import org.personal.mason.feop.oauth.common.client.oauth.RestClient;
import org.personal.mason.feop.oauth.common.model.UserInfo;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/3/13
 * Time: 1:18 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DefaultLoginProcessor implements FOEPLoginProcessor {

    protected static final String STATE = "state";

    private ClientConfiguration configuration;
    protected StateKeyGenerator stateKeyGenerator = new DefaultStateKeyGenerator();
    private String errorRedirectPage = "/error";
    private TokenUtils tokenUtils;

    public DefaultLoginProcessor(ClientConfiguration configuration) {
        this.configuration = configuration;
    }

    public void setConfiguration(ClientConfiguration configuration) {
        this.configuration = configuration;
    }

    public ClientConfiguration getConfiguration() {
        return configuration;
    }

    public void setStateKeyGenerator(StateKeyGenerator stateKeyGenerator) {
        this.stateKeyGenerator = stateKeyGenerator;
    }

    public void setErrorRedirectPage(String errorRedirectPage) {
        this.errorRedirectPage = errorRedirectPage;
    }

    public void setTokenUtils(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    public TokenUtils getTokenUtils() {
        return tokenUtils;
    }

    @Override
    public void authentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(getAuthorizationRequestUrl(request));
    }

    public abstract String getAuthorizationRequestUrl(HttpServletRequest request);

    @Override
    public void retrieveUserInfo(String accessToken) {

        FOEPAuthentication authentication = tokenUtils.getAuthentication(accessToken);

        if (authentication != null) {
            StringBuilder urlPattern = new StringBuilder(configuration.getUserInfoUri());
            if (urlPattern.indexOf("?") > 0) {
                urlPattern.append("&token=%s");
            } else {
                urlPattern.append("?token=%s");
            }

            String uri = String.format(urlPattern.toString(), authentication.getAccessToken());
            UserInfo userInfo = RestClient.getInstance().getResource(uri).accept(MediaType.APPLICATION_JSON_VALUE).get(UserInfo.class);

            authentication.setUserInfo(userInfo);
        }

    }

    @Override
    public String getErrorRedirectPage() {
        return errorRedirectPage;
    }

    @Override
    public void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (getConfiguration().isForceLogin()) {
            String requestUri = request.getRequestURI();
            String loginUri = getConfiguration().getLoginUri();
            if (loginUri != null && !loginUri.isEmpty() && !requestUri.startsWith(loginUri)) {
                response.sendRedirect(loginUri);
            }
        } else {
            response.sendRedirect(getErrorRedirectPage());
        }
    }

    @Override
    public void redirectToSuccessPage(HttpServletRequest request, HttpServletResponse response, FOEPAuthentication authentication) throws IOException {
        if (getConfiguration().getLoginSuccessUri() != null) {
            response.sendRedirect(appendToken(getConfiguration().getLoginSuccessUri(), authentication.getAccessToken()));
        } else {
            response.sendRedirect(appendToken(request.getRequestURI(), authentication.getAccessToken()));
        }
    }



    static String appendToken(String original, String token){
        if(original.contains("?")){
            return original.concat("&token=").concat(token);
        }else {
            return original.concat("?token=").concat(token);
        }


    }
}