package org.personal.mason.feop.oauth.common.client.oauth;

import org.personal.mason.feop.oauth.common.model.UserInfo;

public interface FOEPAuthentication {
    public static String SESSION_AUTHENTICATION = "authentication";

    boolean hasValidToken();

    boolean hasRole(String role);

    public boolean isTokenExpired();

    public String getAccessToken();

    public String getRefreshToken();

    public String getTokenType();

    public String getScope();

    public void setUserInfo(UserInfo userInfo);

    public UserInfo getUserInfo();
}
