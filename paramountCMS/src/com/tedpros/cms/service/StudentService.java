package com.tedpros.cms.service;

import java.util.List;

import com.tedpros.cms.dao.CmsDAO;
import com.tedpros.cms.entity.StudentT;

public interface StudentService extends CmsDAO{

	List<StudentT> findAll();

	List<StudentT> findAllWithExcludesStudents(List<Long> excludeStudents);

}
