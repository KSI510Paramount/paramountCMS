package com.tedpros.cms.service;

import java.util.List;

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
}
