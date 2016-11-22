package com.tedpros.cms.service;

import java.util.List;

import com.tedpros.cms.dao.CmsDAO;
import com.tedpros.cms.entity.CourseOfferT;

public interface CourseOfferService extends CmsDAO{

	List<CourseOfferT> findAll();

	List<CourseOfferT> findByFacultyOidAndSemesterOid(Long facultyOid, Long semesterOid);

	List<CourseOfferT> findByFacultyOid(Long facultyOid);

}
