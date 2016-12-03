package com.tedpros.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tedpros.cms.dao.CmsDAOImpl;
import com.tedpros.cms.entity.CodeValueT;

@Service
public class CodeValueServiceImpl extends CmsDAOImpl implements CodeValueService {

	@Override
	public void addCodeValue(CodeValueT codeValue){
		persist(codeValue);
	}
	
	@Override
	public void updateCodeValue(CodeValueT codeValue){
		update(codeValue);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValueT> findByCodeGroup(String codeGroup){
		Map<String, Object> params= new HashMap<>();
		params.put("codeGroup", codeGroup);
		return (List<CodeValueT>) listByNamedQueryAndParams("CodeValueT.findByCodeGroup", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CodeValueT findByCodeGroupAndCode(String codeGroup, String code){
		Map<String, Object> params= new HashMap<>();
		params.put("codeGroup", codeGroup);
		params.put("code", code);
		return (CodeValueT) singleByNamedQueryAndParams("CodeValueT.findByCodeGroupAndCode", params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CodeValueT> findAll(){
		return (List<CodeValueT>) listByNamedQuery("CodeValueT.findAll");
	}
}
