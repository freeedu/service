package org.personal.mason.feop.oauth.service.domain.service.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthRole;

import java.util.List;

public interface FeopRoleService {

    public List<OauthRole> getDefaultUserRoles();
}
