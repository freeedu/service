package org.personal.mason.feop.oauth.service.spi;

import org.personal.mason.feop.oauth.service.domain.OauthCode;

public interface OAuthCodeService {

	public void save(OauthCode oauthCode);

	public OauthCode findOauthCodeByCode(String code);

	public void delete(OauthCode oauthCode);

}
