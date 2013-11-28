package org.personal.mason.feop.oauth.common.client;


public class XmlClientConfiguration implements ClientConfiguration {
    private String responseType;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String authUrl;
    private String tokenAccessUrl;
    private String scope;
    private String userInfoUri;
    private String oauthRegisterUri;
    private String oauthLogoutUri;
    private String loginSuccessUri;

    private boolean enableCSRF = true;

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getTokenAccessUrl() {
        return tokenAccessUrl;
    }

    public void setTokenAccessUrl(String tokenAccessUrl) {
        this.tokenAccessUrl = tokenAccessUrl;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isEnableCSRF() {
        return enableCSRF;
    }

    public void setEnableCSRF(boolean enableCSRF) {
        this.enableCSRF = enableCSRF;
    }

    public void setUserInfoUri(String userInfoUri) {
        this.userInfoUri = userInfoUri;
    }

    public String getUserInfoUri() {
        return userInfoUri;
    }

    public String getOauthLogoutUri() {
        return oauthLogoutUri;
    }

    public void setOauthLogoutUri(String oauthLogoutUri) {
        this.oauthLogoutUri = oauthLogoutUri;
    }

    public String getOauthRegisterUri() {
        return oauthRegisterUri;
    }

    public void setOauthRegisterUri(String oauthRegisterUri) {
        this.oauthRegisterUri = oauthRegisterUri;
    }

    public void setLoginSuccessUri(String loginSuccessUri) {
        this.loginSuccessUri = loginSuccessUri;
    }

    public String getLoginSuccessUri() {
        return loginSuccessUri;
    }

    @Override
    public void reloadConfiguration() {// DO Nothing
    }
}
