package org.personal.mason.feop.oauth.service.dao.impl;

import org.personal.mason.feop.oauth.service.dao.OauthClientTokenDao;
import org.personal.mason.feop.oauth.service.domain.OauthClientToken;

public class OauthClientTokenDaoImpl extends GenericDaoImpl<OauthClientToken> implements OauthClientTokenDao {
	@Override
	public Class<OauthClientToken> getEntityType() {
		return OauthClientToken.class;
	}
}
