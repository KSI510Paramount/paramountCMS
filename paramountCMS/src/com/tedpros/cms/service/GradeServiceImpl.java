package com.tedpros.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tedpros.cms.dao.CmsDAOImpl;
import com.tedpros.cms.entity.CodeValueT;
import com.tedpros.cms.entity.GradeT;

@Service
public class GradeServiceImpl extends CmsDAOImpl implements GradeService {

	@SuppressWarnings("unchecked")
	@Override
	public List<GradeT> findAll(){
		return (List<GradeT>) listByNamedQuery("GradeT.findAll");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GradeT> findByAssingmentOid(Long assignmentOid){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("assignmentOid", assignmentOid);
		return (List<GradeT>) listByNamedQueryAndParams("GradeT.findByAssingmentOid", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValueT> findAssignmentByCourseOfferOid(Long courseOfferOid){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("courseOfferOid", courseOfferOid);
		return (List<CodeValueT>) listByNamedQueryAndParams("GradeT.findAssignmentByCourseOfferOid", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValueT> findByAssignmentOid(Long assignmentOid){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("assignmentOid", assignmentOid);
		return (List<CodeValueT>) listByNamedQueryAndParams("GradeT.findByAssignmentOid", params);
	}
}
