package org.personal.mason.feop.oauth.service.domain.repository.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthRole;

import java.util.List;

public interface OauthRoleRepositoryCustom {
    List<OauthRole> getDefaultUserRoles(Object... roleNames);
}
