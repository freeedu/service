package org.personal.mason.feop.oauth.service.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T> {

	public abstract T findById(Serializable id);

	public abstract void saveObject(T entity);

	public abstract void udpate(T entity);

	public abstract void removeObject(Class<T> clazz, Serializable id);

	public abstract void removeObject(T entity);

	public abstract List<T> findAll();

}
