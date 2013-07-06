package org.personal.mason.feop.oauth.service.dao.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.personal.mason.feop.oauth.service.dao.GenericDao;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GenericDaoImpl<T> implements GenericDao<T> {
	protected final Log log = LogFactory.getLog(GenericDaoImpl.class);

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		if (entityManager == null) {
			throw new IllegalArgumentException("entityManager cannot be null");
		}
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager.getEntityManagerFactory().createEntityManager();
	}

	public Class<T> getEntityType() {
		throw new NotImplementedException();
	}

	@Override
	public T findById(Serializable id) {
		try {
			return getEntityManager().find(getEntityType(), id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void saveObject(T entity) {
		log.debug("start save entity [" + entity + "]");
		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			log.debug("end save entity [" + entity + "]");
		} catch (Exception e) {
			log.debug("exception save entity [" + entity + "]");
		}

		// EntityManager entityManager = getEntityManager();
		// entityManager.getTransaction().begin();
		// entityManager.persist(entity);
		// entityManager.getTransaction().commit();
	}

	@Override
	public void udpate(T entity) {
		log.debug("start update entity [" + entity + "]");
		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			log.debug("end update entity [" + entity + "]");
		} catch (Exception e) {
			log.debug("exception delete entity [" + entity + "]");
		}

	}

	@Override
	public void removeObject(Class<T> clazz, Serializable id) {
		log.debug("start delete entity  with id [" + id + "]");
		try {
			getEntityManager().remove(findById(id));
			log.debug("end delete entity with id [" + id + "]");
		} catch (Exception e) {
			log.debug("exception delete entity with by id [" + id + "]", e);
		}
	}

	@Override
	public void removeObject(T entity) {
		log.debug("exception delete entity [" + entity + "]");
		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
			log.debug("exception delete entity");
		} catch (Exception e) {
			log.debug("exception delete entity", e);
		}
	}

	@Override
	public List<T> findAll() {
		log.debug("find entities of class [" + getEntityType() + "]");
		try {
			EntityManager entityManager = getEntityManager();
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> criteria = criteriaBuilder.createQuery(getEntityType());
			Root<T> root = criteria.from(getEntityType());
			criteria.select(root);
			return entityManager.createQuery(criteria).getResultList();
		} catch (Exception e) {
			log.debug("exception find entities of class [" + getEntityType() + "]", e);
			return Collections.emptyList();
		}

	}

	/**
	 * @param str
	 * @return boolean
	 */
	public boolean isNullOrSpace(String str) {
		if (str != null && !str.trim().equals("") && !str.trim().equals("null")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @param o
	 * @return boolean
	 */
	public boolean isNullOrSpace(Object o) {
		if (o != null && !o.toString().equals("")) {
			return false;
		} else {
			return true;
		}
	}

}
