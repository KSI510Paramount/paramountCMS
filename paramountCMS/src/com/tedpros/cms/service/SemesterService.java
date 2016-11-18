package com.tedpros.cms.service;

import java.util.List;

import com.tedpros.cms.dao.CmsDAO;
import com.tedpros.cms.entity.FacultyT;
import com.tedpros.cms.entity.SemesterT;

public interface SemesterService extends CmsDAO{

	List<SemesterT> findAll();

}
