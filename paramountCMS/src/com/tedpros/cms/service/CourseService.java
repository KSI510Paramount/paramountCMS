package com.tedpros.cms.service;

import java.util.List;

import com.tedpros.cms.dao.CmsDAO;
import com.tedpros.cms.entity.CourseT;

public interface CourseService extends CmsDAO{

	List<CourseT> findAll();

}
