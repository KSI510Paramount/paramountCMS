package com.tedpros.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tedpros.cms.dao.CmsDAOImpl;
import com.tedpros.cms.entity.EnrollmentT;

@Service
public class EnrollmentServiceImpl extends CmsDAOImpl implements EnrollmentService {

	@SuppressWarnings("unchecked")
	@Override
	public List<EnrollmentT> findAll(){
		return (List<EnrollmentT>) listByNamedQuery("EnrollmentT.findAll");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EnrollmentT> findByCourseOfferOid(Long courseOfferOid){
		Map<String, Object> params = new HashMap<>();
		params.put("courseOfferOid", courseOfferOid);
		return (List<EnrollmentT>) listByNamedQueryAndParams("EnrollmentT.findByCourseOfferOid", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EnrollmentT> findByObjectIds(List<Long> objectids){
		Map<String, Object> params = new HashMap<>();
		params.put("objectids", objectids);
		return (List<EnrollmentT>) listByNamedQueryAndParams("EnrollmentT.findByObjectIds", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EnrollmentT> findByCourseOfferOidExcludeStudentOids( Long courseOfferOid, List<Long> objectids){
		Map<String, Object> params = new HashMap<>();
		params.put("courseOfferOid", courseOfferOid);
		params.put("studentOids", objectids);
		return (List<EnrollmentT>) listByNamedQueryAndParams("EnrollmentT.findByCourseOfferOidExcludeStudentOids", params);
	}
	
}
