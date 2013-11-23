package org.personal.mason.feop.oauth.service.domain.service.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthCode;

public interface FeopAuthCodeService {

	public void save(OauthCode oauthCode);

	public OauthCode findOauthCodeByCode(String code);

	public void delete(OauthCode oauthCode);

}
