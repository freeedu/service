package org.personal.mason.feop.oauth.common.client;

import org.personal.mason.feop.oauth.common.client.oauth.FEOPAuthentication;
import org.personal.mason.feop.oauth.common.client.oauth.RestClient;
import org.personal.mason.feop.oauth.common.model.UserInfo;
import org.personal.mason.feop.oauth.common.utils.Constrains;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

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

    public void retrieveUserInfo(HttpServletRequest request) {
        Map<String, String[]> parms = request.getParameterMap();
        if(!parms.containsKey("token")) {
            return;
        }

        String token = parms.get("token")[0];

        FEOPAuthentication authentication = tokenUtils.getAuthentication(token);

        if (authentication != null) {
            StringBuilder urlPattern = new StringBuilder(configuration.getUserInfoUri());
            if (urlPattern.indexOf("?") > 0) {
                urlPattern.append("&access_token=%s");
            } else {
                urlPattern.append("?access_token=%s");
            }

            String uri = String.format(urlPattern.toString(), authentication.getAccessToken());
            UserInfo userInfo = RestClient.getInstance().getResource(uri).accept(MediaType.APPLICATION_JSON_VALUE).get(UserInfo.class);

            authentication.setUserInfo(userInfo);
        }

    }

    public String getErrorRedirectPage() {
        return errorRedirectPage;
    }
}