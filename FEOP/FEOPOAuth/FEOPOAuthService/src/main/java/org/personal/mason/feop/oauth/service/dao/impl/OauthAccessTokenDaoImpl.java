package org.personal.mason.feop.oauth.service.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.personal.mason.feop.oauth.service.dao.OauthAccessTokenDao;
import org.personal.mason.feop.oauth.service.domain.OauthAccessToken;

public class OauthAccessTokenDaoImpl extends GenericDaoImpl<OauthAccessToken> implements OauthAccessTokenDao {

	@Override
	public Class<OauthAccessToken> getEntityType() {
		return OauthAccessToken.class;
	}

	@Override
	public OauthAccessToken findAccessTokenWithTokenId(String tokenId) {
		try {
			EntityManager entityManager = getEntityManager();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OauthAccessToken> criteria = criteriaBuilder.createQuery(OauthAccessToken.class);
			Root<OauthAccessToken> root = criteria.from(OauthAccessToken.class);
			criteria.where(criteriaBuilder.equal(root.get("tokenId"), tokenId));
			return entityManager.createQuery(criteria).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void removeAccessTokenByTokenId(String tokenId) {
		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaDelete<OauthAccessToken> criteriaDelete = criteriaBuilder.createCriteriaDelete(OauthAccessToken.class);
			Root<OauthAccessToken> root = criteriaDelete.from(OauthAccessToken.class);
			Predicate wherePredicate = criteriaBuilder.equal(root.get("tokenId"), tokenId);
			criteriaDelete.where(wherePredicate);
			Query query = entityManager.createQuery(criteriaDelete);
			query.executeUpdate();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
		}
	}

	@Override
	public void removeAccessTokenByRefreshToken(String refreshToken) {
		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaDelete<OauthAccessToken> criteriaDelete = criteriaBuilder.createCriteriaDelete(OauthAccessToken.class);
			Root<OauthAccessToken> root = criteriaDelete.from(OauthAccessToken.class);
			Predicate wherePredicate = criteriaBuilder.equal(root.get("refreshToken"), refreshToken);
			criteriaDelete.where(wherePredicate);
			Query query = entityManager.createQuery(criteriaDelete);
			query.executeUpdate();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
		}
	}

	@Override
	public OauthAccessToken findAccessTokenWithAuthenticationId(String key) {
		try {
			EntityManager entityManager = getEntityManager();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OauthAccessToken> criteria = criteriaBuilder.createQuery(OauthAccessToken.class);
			Root<OauthAccessToken> root = criteria.from(OauthAccessToken.class);
			criteria.where(criteriaBuilder.equal(root.get("authenticationId"), key));
			return entityManager.createQuery(criteria).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<OauthAccessToken> findAccessTokenWithUsername(String userName) {
		try {
			EntityManager entityManager = getEntityManager();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OauthAccessToken> criteria = criteriaBuilder.createQuery(OauthAccessToken.class);
			Root<OauthAccessToken> root = criteria.from(OauthAccessToken.class);
			criteria.where(criteriaBuilder.equal(root.get("userName"), userName));
			return entityManager.createQuery(criteria).getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<OauthAccessToken> findAccessTokenWithClientId(String clientId) {
		try {
			EntityManager entityManager = getEntityManager();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OauthAccessToken> criteria = criteriaBuilder.createQuery(OauthAccessToken.class);
			Root<OauthAccessToken> root = criteria.from(OauthAccessToken.class);
			criteria.where(criteriaBuilder.equal(root.get("clientId"), clientId));
			return entityManager.createQuery(criteria).getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

}
