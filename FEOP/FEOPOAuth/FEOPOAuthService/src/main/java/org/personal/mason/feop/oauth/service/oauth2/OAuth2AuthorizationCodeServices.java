package org.personal.mason.feop.oauth.service.oauth2;

import org.personal.mason.feop.oauth.service.domain.OauthCode;
import org.personal.mason.feop.oauth.service.spi.OAuthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.code.AuthorizationRequestHolder;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

public class OAuth2AuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

	private OAuthCodeService authCodeService;

	@Autowired
	public void setAuthCodeService(OAuthCodeService authCodeService) {
		if (authCodeService == null) {
			throw new IllegalArgumentException("auth code service cannot be null");
		}
		this.authCodeService = authCodeService;
	}

	@Override
	protected void store(String code, AuthorizationRequestHolder authentication) {
		OauthCode oauthCode = new OauthCode();
		oauthCode.setCode(code);
		oauthCode.setAuthentication(SerializationUtils.serialize(authentication));
		authCodeService.save(oauthCode);
	}

	@Override
	protected AuthorizationRequestHolder remove(String code) {
		AuthorizationRequestHolder authentication;

		OauthCode oauthCode = authCodeService.findOauthCodeByCode(code);

		if (oauthCode != null) {
			authentication = SerializationUtils.deserialize(oauthCode.getAuthentication());
			authCodeService.delete(oauthCode);
		} else {
			authentication = null;
		}

		return authentication;
	}

}
