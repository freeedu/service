package org.personal.mason.feop.oauth.service.dao;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthRole;

public interface OauthRoleDao extends GenericDao<OauthRole> {

	List<OauthRole> getDefaultUserRoles();

}
