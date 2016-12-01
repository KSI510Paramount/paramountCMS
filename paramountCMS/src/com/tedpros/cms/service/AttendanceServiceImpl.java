package com.tedpros.cms.service;

import java.util.List;

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
	
}
