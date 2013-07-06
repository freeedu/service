package org.personal.mason.feop.oauth.service.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.personal.mason.feop.oauth.service.dao.OauthRefreshTokenDao;
import org.personal.mason.feop.oauth.service.domain.OauthRefreshToken;

public class OauthRefreshTokenDaoImpl extends GenericDaoImpl<OauthRefreshToken> implements OauthRefreshTokenDao {

	@Override
	public Class<OauthRefreshToken> getEntityType() {
		return OauthRefreshToken.class;
	}

	@Override
	public OauthRefreshToken findRefreshTokenWithTokenId(String tokenId) {
		try {
			EntityManager entityManager = getEntityManager();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OauthRefreshToken> criteria = criteriaBuilder.createQuery(OauthRefreshToken.class);
			Root<OauthRefreshToken> root = criteria.from(OauthRefreshToken.class);
			criteria.where(criteriaBuilder.equal(root.get("tokenId"), tokenId));
			return entityManager.createQuery(criteria).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void removeRefreshTokenByTokenId(String tokenId) {
		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaDelete<OauthRefreshToken> criteriaDelete = criteriaBuilder.createCriteriaDelete(OauthRefreshToken.class);
			Root<OauthRefreshToken> root = criteriaDelete.from(OauthRefreshToken.class);
			Predicate wherePredicate = criteriaBuilder.equal(root.get("tokenId"), tokenId);
			criteriaDelete.where(wherePredicate);
			Query query = entityManager.createQuery(criteriaDelete);
			query.executeUpdate();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
		}
	}

}
