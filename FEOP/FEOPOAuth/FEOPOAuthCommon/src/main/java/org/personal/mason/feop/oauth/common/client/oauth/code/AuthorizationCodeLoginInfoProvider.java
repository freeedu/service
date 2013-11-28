package org.personal.mason.feop.oauth.common.client.oauth.code;

import org.codehaus.jackson.map.ObjectMapper;
import org.personal.mason.feop.oauth.common.client.oauth.OAuthLoginInfoProvider;
import org.personal.mason.feop.oauth.common.utils.Constrains;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AuthorizationCodeLoginInfoProvider extends OAuthLoginInfoProvider {

    @Override
    public String getAuthorizationRequestUrl(HttpServletRequest request) {

        StringBuilder urlPattern = new StringBuilder();
        List<String> params = new LinkedList<>();
        urlPattern.append("%s?");
        params.add(getConfiguration().getAuthUrl());
        urlPattern.append("client_id=%s");
        params.add(getConfiguration().getClientId());

        urlPattern.append("&response_type=code");

        String callback = request.getRequestURL().toString();
        if (callback != null) {
            try {
                params.add(URLEncoder.encode(callback, "UTF-8"));
                urlPattern.append("&redirect_uri=%s");
            } catch (UnsupportedEncodingException e) {
            }
        }

        if (getConfiguration().getScope() != null) {
            urlPattern.append("&scope=%s");
            params.add(getConfiguration().getScope());
        }

        if (getConfiguration().isEnableCSRF()) {
            urlPattern.append("&state=%s");
            String stateKey = stateKeyGenerator.generateKey();
            params.add(stateKey);
            request.getSession(true).setAttribute(STATE, stateKey);
        }

        return String.format(urlPattern.toString(), params.toArray());
    }

    @Override
    public String getAccessTokenRequestUrl(String callback) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void processAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String error = request.getParameter("error");
        if (error == null) {
            requestAccessToken(request, response);
        } else {
            String errorDesc = request.getParameter("error_description");
            System.out.println(errorDesc);
        }
    }

    private void requestAccessToken(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("code") != null) {
            StringBuilder urlPattern = new StringBuilder();
            List<String> params = new LinkedList<>();
            urlPattern.append("%s?");
            params.add(getConfiguration().getTokenAccessUrl());
            urlPattern.append("client_id=%s");
            params.add(getConfiguration().getClientId());
            urlPattern.append("&client_secret=%s");
            params.add(getConfiguration().getClientSecret());

            urlPattern.append("&grant_type=authorization_code");

            String callback = request.getRequestURL().toString();
            if (callback != null) {
                try {
                    params.add(URLEncoder.encode(callback, "UTF-8"));
                    urlPattern.append("&redirect_uri=%s");
                } catch (UnsupportedEncodingException e) {
                }
            }

            if (getConfiguration().getScope() != null) {
                urlPattern.append("&scope=%s");
                params.add(getConfiguration().getScope());
            }

            String code = request.getParameter("code");
            urlPattern.append("&code=%s");
            params.add(code);

            if (getConfiguration().isEnableCSRF()) {
                urlPattern.append("&state=%s");
                params.add(request.getParameter(STATE));
            }

            try {
                String uri = String.format(urlPattern.toString(), params.toArray());
                ObjectMapper mapper = new ObjectMapper();

                @SuppressWarnings("unchecked")
                Map<String, Object> properties = mapper.readValue(new URL(uri), Map.class);
                HttpSession session = request.getSession(true);

                session.setAttribute(Constrains.AUTHENTICATIOIN, new AuthorizationCodeAuthentication(
                        properties));

                retrieveUserInfo(request);

                if (getConfiguration().getLoginSuccessUri() != null) {
                    response.sendRedirect(getConfiguration().getLoginSuccessUri());
                } else {
                    response.sendRedirect(request.getRequestURI());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isDirectlyRequestToken(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        return referer != null && referer.contains(getConfiguration().getAuthUrl());
    }

}
