package com.tedpros.cms.service;

import java.util.List;

import com.tedpros.cms.dao.CmsDAO;
import com.tedpros.cms.entity.FacultyT;

public interface FacultyService extends CmsDAO{

	List<FacultyT> findAll();

	FacultyT findByUsername(String username);

}
