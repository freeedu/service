package org.personal.mason.feop.oauth.service.repository;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthRole;

public interface OauthRoleRepositoryCustom {
	List<OauthRole> getDefaultUserRoles(Object... roleNames);
}
