package com.tedpros.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tedpros.cms.dao.CmsDAOImpl;
import com.tedpros.cms.entity.CourseOfferT;

@Service
public class CourseOfferServiceImpl extends CmsDAOImpl implements CourseOfferService {

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseOfferT> findAll(){
		return (List<CourseOfferT>) listByNamedQuery("CourseOfferT.findAll");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseOfferT> findByFacultyOidAndSemesterOid(Long facultyOid, Long semesterOid){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("facultyOid", facultyOid);
		params.put("semesterOid", semesterOid);
		return (List<CourseOfferT>) listByNamedQueryAndParams("CourseOfferT.findByFacultyOidAndSemesterOid", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseOfferT> findByFacultyOid(Long facultyOid){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("facultyOid", facultyOid);
		return (List<CourseOfferT>) listByNamedQueryAndParams("CourseOfferT.findByFacultyOid", params);
	}
}
