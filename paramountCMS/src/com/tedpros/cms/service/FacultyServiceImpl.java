package com.tedpros.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tedpros.cms.dao.CmsDAOImpl;
import com.tedpros.cms.entity.FacultyT;

@Service
public class FacultyServiceImpl extends CmsDAOImpl implements FacultyService {

	@SuppressWarnings("unchecked")
	@Override
	public List<FacultyT> findAll(){
		return (List<FacultyT>) listByNamedQuery("FacultyT.findAll");
	}
	
	@Override
	public FacultyT findByUsername(String username){
		Map<String, Object> params= new HashMap<>();
		params.put("username", username);
		return singleByNamedQueryAndParams("FacultyT.findByUsername", params);
	}
}
