package com.tedpros.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tedpros.cms.dao.CmsDAOImpl;
import com.tedpros.cms.entity.StudentT;

@Service
public class StudentServiceImpl extends CmsDAOImpl implements StudentService {

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentT> findAll(){
		return (List<StudentT>) listByNamedQuery("StudentT.findAll");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StudentT> findAllWithExcludesStudents(List<Long> excludeStudents){
		Map<String, Object> params = new HashMap<>();
		params.put("excludeStudents", excludeStudents);
		return (List<StudentT>) listByNamedQueryAndParams("StudentT.findAllWithExcludesStudents", params);
	}
}
