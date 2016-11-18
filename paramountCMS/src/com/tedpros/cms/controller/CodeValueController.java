package com.tedpros.cms.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.tedpros.cms.entity.CodeValueT;
import com.tedpros.cms.service.CodeValueService;

@Controller
@RequestMapping("/codeValue/*")
public class CodeValueController extends TopController{
	
	@Autowired
	private CodeValueService codeValueService;

	@RequestMapping(value = "/getList.do", method=RequestMethod.GET)
	public String getList(WebRequest request){
		List<CodeValueT> codeValueList = codeValueService.findAll();
		request.setAttribute("codeValueList", codeValueList, WebRequest.SCOPE_REQUEST);
		return "codeValue.list";
	}
	
	@RequestMapping(value = "/getAdd.do", method=RequestMethod.GET)
	public String getAdd(WebRequest request){
		return "codeValue.add";
	}
	
	@RequestMapping(value = "/postAdd.do", method=RequestMethod.POST)
	public String postAdd(WebRequest request){
		CodeValueT codeValue = new CodeValueT();
		getDataBinder(request, codeValue);
		codeValueService.addCodeValue(codeValue);
		return "redirect:/codeValue/getList.do";
	}
	
	@RequestMapping(value = "/getEdit.do", method=RequestMethod.GET)
	public String getEdit(WebRequest request){
		String codeValueOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(codeValueOid)){
			CodeValueT codeValue = codeValueService.findById(CodeValueT.class, Long.valueOf(codeValueOid));
			if(codeValue != null){
				request.setAttribute("codeValue", codeValue, WebRequest.SCOPE_REQUEST);
			}
		}
		return "codeValue.edit";
	}
	
	@RequestMapping(value = "/postEdit.do", method=RequestMethod.POST)
	public String postEdit(WebRequest request){
		String codeValueOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(codeValueOid)){
			CodeValueT codeValue = codeValueService.findById(CodeValueT.class, Long.valueOf(codeValueOid));
			if(codeValue != null){
				getDataBinder(request, codeValue);
				codeValueService.updateCodeValue(codeValue);
			}
		}
		return "redirect:/codeValue/getList.do";
	}
	
	@RequestMapping(value = "/getDelete.do", method=RequestMethod.GET)
	public String getDelete(WebRequest request){
		String codeValueOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(codeValueOid)){
			CodeValueT codeValue = codeValueService.findById(CodeValueT.class, Long.valueOf(codeValueOid));
			if(codeValue != null){
				codeValueService.delete(codeValue);
			}
		}
		
		return "redirect:/codeValue/getList.do";
	}
	
}
