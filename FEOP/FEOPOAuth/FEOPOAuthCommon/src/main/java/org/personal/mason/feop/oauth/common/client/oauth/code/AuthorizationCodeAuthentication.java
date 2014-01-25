package org.personal.mason.feop.oauth.common.client.oauth.code;

import org.personal.mason.feop.oauth.common.client.oauth.FOEPAuthentication;
import org.personal.mason.feop.oauth.common.model.UserInfo;
import org.personal.mason.feop.oauth.common.model.UserRole;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class AuthorizationCodeAuthentication implements FOEPAuthentication {
    private static final String ACCESS_TOKEN = "access_token";
    private static final String TOKEN_TYPE = "token_type";
    private static final String EXPIRES_IN = "expires_in";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String SCOPE = "scope";
    private static final String STATE = "state";

    private String accessToken;
    private String tokenType;
    private int expiresIn;
    private Date expiredAt;
    private String refreshToken;
    private String scope;
    private UserInfo userInfo;

    public AuthorizationCodeAuthentication(Map<String, Object> properties) {
        accessToken = (String) properties.get(ACCESS_TOKEN);
        tokenType = (String) properties.get(TOKEN_TYPE);
        expiresIn = (Integer) properties.get(EXPIRES_IN);
        expiresIn = expiresIn > 0 ? expiresIn : 0;
        expiredAt = calculateExpiresTime(expiresIn);
        refreshToken = (String) properties.get(REFRESH_TOKEN);
        scope = (String) properties.get(SCOPE);
    }

    @Override
    public boolean updateProperties(Map<String, Object> properties) {
        try {
            accessToken = (String) properties.get(ACCESS_TOKEN);
            tokenType = (String) properties.get(TOKEN_TYPE);
            expiresIn = (Integer) properties.get(EXPIRES_IN);
            expiresIn = expiresIn > 0 ? expiresIn : 0;
            expiredAt = calculateExpiresTime(expiresIn);
            refreshToken = (String) properties.get(REFRESH_TOKEN);
            return true;
        } catch (Exception e){
        }
        return false;
    }

    @Override
    public boolean hasValidToken() {
        return accessToken != null && !isTokenExpired();
    }

    @Override
    public boolean hasRole(String role) {
        if(userInfo == null){
            return false;
        }

        if(userInfo.getRoles() == null || userInfo.getRoles().isEmpty()){
            return false;
        }

        for (UserRole r : userInfo.getRoles()){
            if(r.getRoleName().equalsIgnoreCase(role)){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isTokenExpired() {
        return Calendar.getInstance().getTime().after(expiredAt);
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public String getTokenType() {
        return tokenType;
    }

    @Override
    public String getScope() {
        return scope;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    private static Date calculateExpiresTime(int expiresIn){
        Calendar instance = Calendar.getInstance();
        if(expiresIn > 0){
            instance.add(Calendar.SECOND, expiresIn);
        }
        return instance.getTime();
    }
}
