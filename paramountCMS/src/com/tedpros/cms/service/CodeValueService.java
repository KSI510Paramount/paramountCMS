package com.tedpros.cms.service;

import java.util.List;

import com.tedpros.cms.dao.CmsDAO;
import com.tedpros.cms.entity.CodeValueT;

public interface CodeValueService extends CmsDAO{

	void addCodeValue(CodeValueT codeValue);

	List<CodeValueT> findByCodeGroup(String codeGroup);

	List<CodeValueT> findAll();

	void updateCodeValue(CodeValueT codeValue);

	CodeValueT findByCodeGroupAndCode(String codeGroup, String code);
}
