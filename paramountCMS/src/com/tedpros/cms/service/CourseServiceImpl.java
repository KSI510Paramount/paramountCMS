package com.tedpros.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tedpros.cms.dao.CmsDAOImpl;
import com.tedpros.cms.entity.CourseT;

@Service
public class CourseServiceImpl extends CmsDAOImpl implements CourseService {

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseT> findAll(){
		return (List<CourseT>) listByNamedQuery("CourseT.findAll");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseT> findAllWithExcludesCourse(List<Long> objectiIds){
		Map<String, Object> params = new HashMap<>();
		params.put("excludeCourse", objectiIds);
		return (List<CourseT>) listByNamedQueryAndParams("CourseT.findAllWithExcludesCourse", params);
	}
}
