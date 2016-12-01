package com.tedpros.cms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
		return "codeValue.list";
	}
	
	@RequestMapping(value = "/getResultList.do", method=RequestMethod.GET)
	@ResponseBody
	public String getResultList(WebRequest request){
		List<CodeValueT> codeValueList = codeValueService.findAll();
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("recordsTotal", codeValueList.size());
		jsonMap.put("recordsFiltered", codeValueList.size());
		jsonMap.put("data", getCodeValueList(codeValueList));
		String jsonString = new JSONObject(jsonMap).toJSONString();
		System.out.println(jsonString);
		return jsonString;
	}
	
	private List<Object> getCodeValueList(List<CodeValueT> codeValues){
		List<Object> cv = new ArrayList<>();
		for (CodeValueT codeValue : codeValues) {
			Map<String, Object> valueMap = new HashMap<>();
			valueMap.put("codeGroup", codeValue.getCodeGroup());
			valueMap.put("code", codeValue.getCode());
			valueMap.put("shortDescription", codeValue.getShortDescription());
			valueMap.put("longDescription", codeValue.getLongDescription());
			valueMap.put("objectid", codeValue.getObjectid());
			cv.add(valueMap);
		}
		return cv;
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
	
	@RequestMapping(value = "/assignment/getAdd.do", method=RequestMethod.GET)
	public String getAddAssignment(WebRequest request){
		request.setAttribute("courseOfferOid", request.getParameter("objectid"), WebRequest.SCOPE_REQUEST);
		return "codeValue.assignment.add";
	}
	
	@RequestMapping(value = "/assignment/postAdd.do", method=RequestMethod.POST)
	public String postAddAssignment(WebRequest request){
		String courseOfferOid = request.getParameter("objectid");
		String assignmentTitle = request.getParameter("assignmentTitle");
		if(StringUtils.isNotBlank(assignmentTitle)){
			CodeValueT codeValue = new CodeValueT();
			codeValue.setCodeGroup("ASSIGNMENT");
			codeValue.setLongDescription(assignmentTitle);
			codeValue.setShortDescription(assignmentTitle);
			codeValue.setCode(buildCode(assignmentTitle));
			codeValueService.addCodeValue(codeValue);
		}
		
		return "redirect:/grade/getView.do?objectid="+courseOfferOid;
	}
	
	private String buildCode(String title){
		StringBuffer sb = new StringBuffer(); 
		String[] strings = title.toUpperCase().split(" ");
		if(strings != null && strings.length > 1){
			for (String s : strings) {
				for(int i = 0; i < s.length(); i++){
					sb.append(s.charAt(i));
					if(i == 1)break;
				}
			}
		}else{
			sb.append(title);
		}
		return sb.toString();
	}
}
