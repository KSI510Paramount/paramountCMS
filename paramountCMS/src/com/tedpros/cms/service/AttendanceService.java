package com.tedpros.cms.service;

import java.util.List;

import com.tedpros.cms.dao.CmsDAO;
import com.tedpros.cms.entity.AttendanceT;

public interface AttendanceService extends CmsDAO{

	List<AttendanceT> findAll();

	List<AttendanceT> findByCourseOfferOid(Long courseOfferOid);


}
