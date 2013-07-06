package org.personal.mason.feop.oauth.service.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.personal.mason.feop.oauth.service.dao.OauthClientDetailDao;
import org.personal.mason.feop.oauth.service.domain.OauthClientDetail;

public class OauthClientDetailDaoImpl extends GenericDaoImpl<OauthClientDetail> implements OauthClientDetailDao {

	@Override
	public Class<OauthClientDetail> getEntityType() {
		return OauthClientDetail.class;
	}

	@Override
	public boolean saveClient(OauthClientDetail client) {
		saveObject(client);
		return client.getId() != null;
	}

	@Override
	public OauthClientDetail findByClientId(String clientId) {
		try {
			EntityManager entityManager = getEntityManager();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OauthClientDetail> criteria = criteriaBuilder.createQuery(OauthClientDetail.class);
			Root<OauthClientDetail> root = criteria.from(OauthClientDetail.class);

			Predicate wherePredicate = criteriaBuilder.equal(root.get("clientId"), clientId);
			criteria.distinct(true).select(root).where(wherePredicate);
			return entityManager.createQuery(criteria).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<OauthClientDetail> findByOwner(String owner) {
		try {
			EntityManager entityManager = getEntityManager();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OauthClientDetail> criteria = criteriaBuilder.createQuery(OauthClientDetail.class);
			Root<OauthClientDetail> root = criteria.from(OauthClientDetail.class);

			Predicate wherePredicate = criteriaBuilder.equal(root.get("owner"), owner);
			criteria.select(root).where(wherePredicate);
			return entityManager.createQuery(criteria).getResultList();
		} catch (Exception ex) {
			return Collections.emptyList();
		}
	}

}
