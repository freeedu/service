package org.personal.mason.feop.oauth.service.spi;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthRole;

public interface FeopRoleService {

	public List<OauthRole> getDefaultUserRoles();
}
