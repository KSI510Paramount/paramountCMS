package com.tedpros.cms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tedpros.cms.entity.DomainEntity;

public abstract class CmsDAOImpl implements CmsDAO {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T persist(T entity) {
    	DomainEntity mergeEntity = (DomainEntity) entity;
    	mergeEntity.prePersist();
		Session session = getOpenSession();
		Transaction tx = session.beginTransaction();
		session.persist(mergeEntity);
		tx.commit();
		session.close();
		return ((T) mergeEntity);
	}
    
	@SuppressWarnings("unchecked")
	@Override
	public <T> T update(T entity) {
		DomainEntity mergeEntity = (DomainEntity) entity;
		mergeEntity.preUpdate();
		Session session = getOpenSession();
		Transaction tx = session.beginTransaction();
		mergeEntity = (DomainEntity) session.merge(mergeEntity);
		tx.commit();
		session.close();
		return ((T) mergeEntity);
	}
	
	@Override
    public <T> void delete(T entity) {
    	DomainEntity mergeEntity = (DomainEntity) entity;
    	Session session = getOpenSession();
		Transaction tx = session.beginTransaction();
		session.remove(mergeEntity);
		tx.commit();
		session.close();		
    }
	
	@Override
	public List<?> listByNamedQuery(String queryName){
		List<?> results = new ArrayList<>();
		Session session = getOpenSession();
		Query query = session.createNamedQuery(queryName);
		results = query.getResultList();
		session.close();
		return results;
	}

	@Override
	public List<?> listByNamedQueryAndParams(String queryName, Map<String, Object> params){
		List<?> results = new ArrayList<>();
		Session session = getOpenSession();
		Query query = session.createNamedQuery(queryName);
		for (Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		results = query.getResultList();
		session.close();
		return results;
	}
	
	@Override
	public <T> T singleByNamedQueryAndParams(String queryName, Map<String, Object> params){
		T results = null;
		Session session = getOpenSession();
		Query query = session.createNamedQuery(queryName);
		for (Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		results = (T) query.getSingleResult();
		session.close();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T findById(Class<?> entityClass, Long id) {
		Session session = getOpenSession();
		T entity = (T) session.find(entityClass, id);
		session.close();
		return (entity);
	}
	
	public Session getOpenSession(){
		return GenericDAO.getSessionFactory().openSession();
	}
}
