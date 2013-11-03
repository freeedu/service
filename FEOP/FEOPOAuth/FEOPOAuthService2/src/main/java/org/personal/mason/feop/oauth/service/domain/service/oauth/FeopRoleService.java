package org.personal.mason.feop.oauth.service.domain.service.oauth;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthRole;

public interface FeopRoleService {

	public List<OauthRole> getDefaultUserRoles();
}
