package org.personal.mason.feop.oauth.service.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.personal.mason.feop.oauth.service.domain.OauthRole;

public class OauthRoleRepositoryImpl implements OauthRoleRepositoryCustom {
	private final static String DEFAULT_USER_ROLE = "ROLE_USER";

	private List<OauthRole> defaultUserRoles = null;

	@PersistenceContext
	private EntityManager em;

	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	@Override
	public List<OauthRole> getDefaultUserRoles() {
		if (defaultUserRoles != null) {
			return defaultUserRoles;
		}

		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<OauthRole> criteria = criteriaBuilder.createQuery(OauthRole.class);
			Root<OauthRole> root = criteria.from(OauthRole.class);
			Predicate roleNamePredicate = criteriaBuilder.equal(root.get("name"), DEFAULT_USER_ROLE);
			Predicate enablePredicate = criteriaBuilder.equal(root.get("enabled"), true);
			Predicate wherePredicate = criteriaBuilder.and(roleNamePredicate, enablePredicate);
			criteria.distinct(true).select(root).where(wherePredicate);
			defaultUserRoles = em.createQuery(criteria).getResultList();
		} catch (Exception e) {
			defaultUserRoles = new ArrayList<>();
		}

		return defaultUserRoles;
	}

}
