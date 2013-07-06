package org.personal.mason.feop.oauth.service.dao;

import org.personal.mason.feop.oauth.service.domain.OauthCode;

public interface OauthCodeDao extends GenericDao<OauthCode>{

	OauthCode findOauthCodeByCode(String code);

}
