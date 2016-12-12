package com.tedpros.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tedpros.cms.dao.CmsDAOImpl;
import com.tedpros.cms.entity.AttendanceT;

@Service
public class AttendanceServiceImpl extends CmsDAOImpl implements AttendanceService {

	@SuppressWarnings("unchecked")
	@Override
	public List<AttendanceT> findAll(){
		return (List<AttendanceT>) listByNamedQuery("AttendanceT.findAll");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AttendanceT> findByCourseOfferOid(Long courseOfferOid){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("courseOfferOid", courseOfferOid);
		return (List<AttendanceT>) listByNamedQueryAndParams("AttendanceT.findByCourseOfferOid", params);
	}
	
}
