package org.personal.mason.feop.oauth.service.common.oauth2;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthCode;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopAuthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.code.AuthorizationRequestHolder;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

public class OAuth2AuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

	private FeopAuthCodeService feopAuthCodeService;

	@Autowired
	public void setFeopAuthCodeService(FeopAuthCodeService feopAuthCodeService) {
		this.feopAuthCodeService = feopAuthCodeService;
	}

	@Override
	protected void store(String code, AuthorizationRequestHolder authentication) {
		OauthCode oauthCode = new OauthCode();
		oauthCode.setCode(code);
		oauthCode.setAuthentication(SerializationUtils.serialize(authentication));
		feopAuthCodeService.save(oauthCode);
	}

	@Override
	protected AuthorizationRequestHolder remove(String code) {
		AuthorizationRequestHolder authentication;

		OauthCode oauthCode = feopAuthCodeService.findOauthCodeByCode(code);

		if (oauthCode != null) {
			authentication = SerializationUtils.deserialize(oauthCode.getAuthentication());
			feopAuthCodeService.delete(oauthCode);
		} else {
			authentication = null;
		}

		return authentication;
	}

}
