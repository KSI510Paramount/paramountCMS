package com.tedpros.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tedpros.cms.dao.CmsDAOImpl;
import com.tedpros.cms.entity.SemesterT;

@Service
public class SemesterServiceImpl extends CmsDAOImpl implements SemesterService {

	@SuppressWarnings("unchecked")
	@Override
	public List<SemesterT> findAll(){
		return (List<SemesterT>) listByNamedQuery("SemesterT.findAll");
	}
}
