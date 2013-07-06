package org.personal.mason.feop.oauth.service.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.personal.mason.feop.oauth.service.dao.OauthCodeDao;
import org.personal.mason.feop.oauth.service.domain.OauthCode;

public class OauthCodeDaoImpl extends GenericDaoImpl<OauthCode> implements OauthCodeDao {
	
	@Override
	public Class<OauthCode> getEntityType() {
		return OauthCode.class;
	}

	@Override
	public OauthCode findOauthCodeByCode(String code) {
		try {
			EntityManager entityManager = getEntityManager();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OauthCode> criteria = criteriaBuilder.createQuery(OauthCode.class);
			Root<OauthCode> root = criteria.from(OauthCode.class);
			Predicate wherePredicate = criteriaBuilder.equal(root.get("code"), code);
			criteria.distinct(true).select(root).where(wherePredicate);
			return entityManager.createQuery(criteria).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
