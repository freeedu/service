package org.personal.mason.feop.oauth.service.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.personal.mason.feop.oauth.service.dao.OauthUserDao;
import org.personal.mason.feop.oauth.service.domain.OauthRole;
import org.personal.mason.feop.oauth.service.domain.OauthUser;

public class OauthUserDaoImpl extends GenericDaoImpl<OauthUser> implements OauthUserDao {

	@Override
	public Class<OauthUser> getEntityType() {
		return OauthUser.class;
	}

	@Override
	public OauthUser findByEmailOrUsername(String emailOrUsername) {
		try {
			EntityManager entityManager = getEntityManager();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OauthUser> criteria = criteriaBuilder.createQuery(getEntityType());
			Root<OauthUser> root = criteria.from(getEntityType());
			Predicate emailPredicate = criteriaBuilder.equal(root.get("email"), emailOrUsername);
			Predicate usernamePredicate = criteriaBuilder.equal(root.get("userName"), emailOrUsername);
			Predicate wherePredicate = criteriaBuilder.or(usernamePredicate, emailPredicate);
			criteria.distinct(true).select(root).where(wherePredicate);
			return entityManager.createQuery(criteria).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<String> findUserRoles(OauthUser user) {
		List<OauthRole> roles = user.getRoles();
		List<String> roleNames = new ArrayList<>();

		for (OauthRole role : roles) {
			if (role.getEnabled()) {
				roleNames.add(role.getName());
			}
		}
		return roleNames;
	}

}
