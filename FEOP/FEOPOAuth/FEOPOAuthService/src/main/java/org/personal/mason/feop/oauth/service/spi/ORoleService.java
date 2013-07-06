package org.personal.mason.feop.oauth.service.spi;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthRole;

public interface ORoleService {

	public List<OauthRole> getDefaultUserRoles();
}
