package com.tedpros.cms.dao;

import java.util.List;
import java.util.Map;

public interface CmsDAO {

	<T> T persist(T entity);

	<T> T update(T entity);

	<T> void delete(T entity);

	List<?> listByNamedQuery(String queryName);

	List<?> listByNamedQueryAndParams(String queryName, Map<String, Object> params);

	<T> T findById(Class<?> entityClass, Long id);

	<T> T singleByNamedQueryAndParams(String queryName, Map<String, Object> params);

}
