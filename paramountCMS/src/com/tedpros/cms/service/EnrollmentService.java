package com.tedpros.cms.service;

import java.util.List;

import com.tedpros.cms.dao.CmsDAO;
import com.tedpros.cms.entity.EnrollmentT;

public interface EnrollmentService extends CmsDAO{

	List<EnrollmentT> findAll();

	List<EnrollmentT> findByCourseOfferOid(Long courseOfferOid);

	List<EnrollmentT> findByObjectIds(List<Long> objectids);

	List<EnrollmentT> findByCourseOfferOidExcludeStudentOids(Long courseOfferOid, List<Long> objectids);

}
