package org.personal.mason.feop.oauth.common.oauth;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public interface AccessTokenConverter {
	AppToken convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication);
}
