package org.personal.mason.feop.oauth.common.client.oauth;

import org.personal.mason.feop.oauth.common.model.UserInfo;

import java.util.Map;

public interface FOEPAuthentication {
    public static String SESSION_AUTHENTICATION = "authentication";
    public static String COOKIE_AUTHENTICATION = "cookie-token";
    public static String HEADER_AUTHENTICATION = "header-token";

    boolean hasValidToken();

    boolean hasRole(String role);

    public boolean isTokenExpired();

    public String getAccessToken();

    public String getRefreshToken();

    public String getTokenType();

    public String getScope();

    public void setUserInfo(UserInfo userInfo);

    public UserInfo getUserInfo();

    boolean updateProperties(Map<String, Object> properties);

}
