package org.personal.mason.feop.oauth.service.domain.service.common.impl;

import java.util.Arrays;
import java.util.List;

public enum AuthorizationType {
    AuthorizationCode("authorization_code", "ROLE_CLIENT", "read,write", true, 3600, 3600),
    Implicit("implicit", "ROLE_CLIENT", "read,write", false, 3600, 3600),
    ResourceOwnerPassword("password", "ROLE_CLIENT", "read,write", true, 3600, 3600),
    ClientCrdentials("client_credentials", "ROLE_CLIENT", "read,write", false, 3600, 3600);

    private String grantType;
    private String authorities;
    private String scope;
    private boolean supportRefresh;
    private int accessTokenValidity;
    private int refreshTokenValidity;

    private AuthorizationType(String grantType, String authorities, String scope, boolean supportRefresh, int accessTokenValidity, int refreshTokenValidity) {
        this.grantType = grantType;
        this.authorities = authorities;
        this.scope = scope;
        this.supportRefresh = supportRefresh;
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getGrantType() {
        if(supportRefresh){
            return grantType + ",refresh_token";
        }
        return grantType;
    }

    public String getAuthorities() {
        return authorities;
    }

    public String getScope() {
        return scope;
    }

    public int getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public int getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public static List<String> getAllTypes() {
        return Arrays.asList(AuthorizationCode.name(), Implicit.name(), ResourceOwnerPassword.name(), ClientCrdentials.name());
    }

    public static AuthorizationType getClientTypeWithName(String name) {
        for (AuthorizationType type : AuthorizationType.values()) {
            if (type.name().endsWith(name)) {
                return type;
            }
        }

        throw new IllegalArgumentException(String.format("Cannot found a type with name %s", name));
    }
}
