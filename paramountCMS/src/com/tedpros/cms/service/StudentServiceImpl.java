package com.tedpros.cms.service;

import java.util.List;

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
}
