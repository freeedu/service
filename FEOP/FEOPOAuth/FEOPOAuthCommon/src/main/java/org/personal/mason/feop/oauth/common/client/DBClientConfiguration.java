package org.personal.mason.feop.oauth.common.client;

import org.personal.mason.feop.oauth.common.domain.model.SystemSetting;
import org.personal.mason.feop.oauth.common.spi.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBClientConfiguration implements ClientConfiguration {

    private final static Object RELOAD_LOCK = new Object();
    private String profileKey = "FEOP_OAUTH";

    private SystemSettingService systemSettingService;

    private Map<String, String> config = new HashMap<>();

    public void setProfileKey(String profileKey) {
        this.profileKey = profileKey;
    }

    @Autowired
    public void setSystemSettingService(SystemSettingService systemSettingService) {
        this.systemSettingService = systemSettingService;
    }

    public DBClientConfiguration() {
    }

    public DBClientConfiguration(String profileKey) {
        this.profileKey = profileKey;
    }

    public Map<String, String> getConfig() {
        if (config.isEmpty()) {
            List<SystemSetting> findByProfile = systemSettingService.findByProfile(profileKey);
            for (SystemSetting settings : findByProfile) {
                config.put(settings.getKey(), settings.getValue());
            }
        }
        return config;
    }

    public static List<String> getAllOauthSettings() {
        return Arrays.asList(RESPONSE_TYPE, CLIENT_ID, CLIENT_SECRET, REDIRECT_URI, AUTH_URI, TOKEN_ACCESS_URI, SCOPE, ENABLE_CSRF, USER_INFO_URI,
                OAUTH_LOGOUT_URI, OAUTH_REGISTER_URI, LOGIN_SUCCESS_URI);
    }

    @Override
    public String getResponseType() {
        return getConfig().get(RESPONSE_TYPE);
    }

    @Override
    public String getClientId() {
        return getConfig().get(CLIENT_ID);
    }

    @Override
    public String getClientSecret() {
        return getConfig().get(CLIENT_SECRET);
    }

    @Override
    public String getRedirectUri() {
        return getConfig().get(REDIRECT_URI);
    }

    @Override
    public String getAuthUrl() {
        return getConfig().get(AUTH_URI);
    }

    @Override
    public String getTokenAccessUrl() {
        return getConfig().get(TOKEN_ACCESS_URI);
    }

    @Override
    public String getScope() {
        return getConfig().get(SCOPE);
    }

    @Override
    public boolean isEnableCSRF() {
        String s = getConfig().get(ENABLE_CSRF);
        if (s != null) {
            return Boolean.valueOf(s);
        }
        return true;
    }

    @Override
    public String getUserInfoUri() {
        return getConfig().get(USER_INFO_URI);
    }

    @Override
    public String getOauthLogoutUri() {
        return getConfig().get(OAUTH_LOGOUT_URI);
    }

    @Override
    public String getOauthRegisterUri() {
        return getConfig().get(OAUTH_REGISTER_URI);
    }

    @Override
    public String getLoginSuccessUri() {
        return getConfig().get(LOGIN_SUCCESS_URI);
    }

    @Override
    public void reloadConfiguration() {
        synchronized (RELOAD_LOCK) {
            config.clear();
            List<SystemSetting> findByProfile = systemSettingService.findByProfile(profileKey);
            for (SystemSetting settings : findByProfile) {
                config.put(settings.getKey(), settings.getValue());
            }
        }
    }
}
