package org.personal.mason.feop.oauth.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.personal.mason.feop.oauth.service.dao.OauthRoleDao;
import org.personal.mason.feop.oauth.service.domain.OauthRole;

public class OauthRoleDaoImpl extends GenericDaoImpl<OauthRole> implements OauthRoleDao {
	private final static String DEFAULT_USER_ROLE = "ROLE_USER";

	private List<OauthRole> defaultUserRoles = null;

	@Override
	public Class<OauthRole> getEntityType() {
		return OauthRole.class;
	}

	@Override
	public List<OauthRole> getDefaultUserRoles() {

		if (defaultUserRoles != null) {
			return defaultUserRoles;
		}

		try {
			EntityManager entityManager = getEntityManager();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OauthRole> criteria = criteriaBuilder.createQuery(getEntityType());
			Root<OauthRole> root = criteria.from(getEntityType());
			Predicate roleNamePredicate = criteriaBuilder.equal(root.get("name"), DEFAULT_USER_ROLE);
			Predicate enablePredicate = criteriaBuilder.equal(root.get("enabled"), true);
			Predicate wherePredicate = criteriaBuilder.and(roleNamePredicate, enablePredicate);
			criteria.distinct(true).select(root).where(wherePredicate);
			defaultUserRoles = entityManager.createQuery(criteria).getResultList();
		} catch (Exception e) {
			defaultUserRoles = new ArrayList<>();
		}

		return defaultUserRoles;
	}

}
