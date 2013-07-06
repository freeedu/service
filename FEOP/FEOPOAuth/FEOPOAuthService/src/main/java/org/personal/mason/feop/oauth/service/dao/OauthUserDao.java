package org.personal.mason.feop.oauth.service.dao;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthUser;

public interface OauthUserDao extends GenericDao<OauthUser> {

	/**
	 * Query User by email or username
	 * 
	 * @param emailOrUsername
	 * @return
	 */
	public OauthUser findByEmailOrUsername(String emailOrUsername);

	/**
	 * Query user Roles
	 * 
	 * @param user
	 * @return
	 */
	public List<String> findUserRoles(OauthUser user);

}
