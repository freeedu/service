package org.personal.mason.feop.oauth.common.client;


public interface ClientConfiguration {
    static final String RESPONSE_TYPE = "response.type";
    static final String CLIENT_ID = "client.id";
    static final String CLIENT_SECRET = "client.secret";
    static final String REDIRECT_URI = "redirect.uri";
    static final String AUTH_URI = "user.authorization.uri";
    static final String TOKEN_ACCESS_URI = "access.token.uri";
    static final String SCOPE = "scope";
    static final String ENABLE_CSRF = "csrf.enable";
    static final String USER_INFO_URI = "user.info.uri";
    static final String OAUTH_LOGOUT_URI = "oauth.logout.uri";
    static final String OAUTH_REGISTER_URI = "oauth.register.uri";
    static final String LOGIN_SUCCESS_URI = "login.success.uri";

    public String getResponseType();

    public String getClientId();

    public String getClientSecret();

    public String getRedirectUri();

    public String getAuthUrl();

    public String getTokenAccessUrl();

    public String getScope();

    public boolean isEnableCSRF();

    public String getUserInfoUri();

    public String getOauthLogoutUri();

    public String getOauthRegisterUri();

    public String getLoginSuccessUri();

    public void reloadConfiguration();
}
