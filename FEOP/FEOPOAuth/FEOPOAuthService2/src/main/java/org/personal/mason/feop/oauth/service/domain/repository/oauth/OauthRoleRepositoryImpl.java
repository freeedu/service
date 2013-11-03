package org.personal.mason.feop.oauth.service.domain.repository.oauth;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthRole;
import org.springframework.beans.factory.annotation.Qualifier;

public class OauthRoleRepositoryImpl implements OauthRoleRepositoryCustom {
	private final static String DEFAULT_USER_ROLE = "ROLE_USER";

	@PersistenceContext(unitName="oauth2")
	@Qualifier(value="entityManagerFactory")
	private EntityManager em;

	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	@Override
	public List<OauthRole> getDefaultUserRoles(Object... roleNames) {
		try {
			if (roleNames.length == 0) {
				roleNames = new Object[] { DEFAULT_USER_ROLE };
			}

			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<OauthRole> criteria = criteriaBuilder.createQuery(OauthRole.class);
			Root<OauthRole> root = criteria.from(OauthRole.class);
			Predicate roleNamePredicate = root.get("name").in(roleNames);
			Predicate enablePredicate = criteriaBuilder.equal(root.get("enabled"), true);
			Predicate wherePredicate = criteriaBuilder.and(roleNamePredicate, enablePredicate);
			criteria.distinct(true).select(root).where(wherePredicate);
			return em.createQuery(criteria).getResultList();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

}
