package org.personal.mason.feop.oauth.service.domain.repository.oauth;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthRole;

public interface OauthRoleRepositoryCustom {
	List<OauthRole> getDefaultUserRoles(Object... roleNames);
}
