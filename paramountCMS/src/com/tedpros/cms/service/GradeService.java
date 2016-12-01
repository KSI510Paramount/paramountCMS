package com.tedpros.cms.service;

import java.util.List;

import com.tedpros.cms.dao.CmsDAO;
import com.tedpros.cms.entity.CodeValueT;
import com.tedpros.cms.entity.GradeT;

public interface GradeService extends CmsDAO{

	List<GradeT> findAll();

	List<GradeT> findByAssingmentOid(Long assignmentOid);

	List<CodeValueT> findAssignmentByCourseOfferOid(Long courseOfferOid);

	List<CodeValueT> findByAssignmentOid(Long assignmentOid);

}
