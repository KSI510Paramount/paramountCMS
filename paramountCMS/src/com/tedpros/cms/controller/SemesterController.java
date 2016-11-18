package com.tedpros.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.tedpros.cms.entity.CodeValueT;
import com.tedpros.cms.entity.SemesterT;
import com.tedpros.cms.service.CodeValueService;
import com.tedpros.cms.service.SemesterService;

@Controller
@RequestMapping("/semester/*")
public class SemesterController extends TopController{
	
	@Autowired
	private SemesterService semesterService;
	
	@Autowired
	private CodeValueService codeValueService;
	
	@RequestMapping(value = "/getList.do", method=RequestMethod.GET)
	public String getList(WebRequest request){
		List<SemesterT> semesterList = semesterService.findAll();
		request.setAttribute("semesterList", semesterList, WebRequest.SCOPE_REQUEST);
		return "semester.list";
	}
	
	@RequestMapping(value = "/getAdd.do", method=RequestMethod.GET)
	public String getAdd(WebRequest request){
		return "semester.add";
	}
	
	@RequestMapping(value = "/postAdd.do", method=RequestMethod.POST)
	public String postAdd(WebRequest request){
		try {
	    	SemesterT semester = new SemesterT();
	    	getDataBinder(request, semester);
	    	String semesterTypeOid = request.getParameter("semesterTypeOid");
	    	if(StringUtils.isNotBlank(semesterTypeOid)){
	    		CodeValueT semesterTypeCode = codeValueService.findById(CodeValueT.class, Long.valueOf(semesterTypeOid));
	    		if(semesterTypeCode != null){
	    			semester.setSemesterTypeOid(semesterTypeCode);
	    		}
	    	}
		    semesterService.persist(semester);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/semester/getList.do";
	}
	
	@RequestMapping(value = "/getEdit.do", method=RequestMethod.GET)
	public String getEdit(WebRequest request){
		String semesterOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(semesterOid)){
			SemesterT semester = semesterService.findById(SemesterT.class, Long.valueOf(semesterOid));
			if(semester != null){
				request.setAttribute("semester", semester, WebRequest.SCOPE_REQUEST);
			}
		}
		return "semester.edit";
	}
	
	@RequestMapping(value = "/postEdit.do", method=RequestMethod.POST)
	public String postEdit(WebRequest request, HttpServletRequest httpRequest){
		 try {
			 	String semesterOid= request.getParameter("objectid");
				if(StringUtils.isNotBlank(semesterOid)){
					SemesterT semester = semesterService.findById(SemesterT.class, Long.valueOf(semesterOid));
					if(semester != null){
				    	getDataBinder(request, semester);
				    	String semesterTypeOid = request.getParameter("semesterTypeOid");
				    	if(StringUtils.isNotBlank(semesterTypeOid)){
				    		CodeValueT semesterTypeCode = codeValueService.findById(CodeValueT.class, Long.valueOf(semesterTypeOid));
				    		if(semesterTypeCode != null){
				    			semester.setSemesterTypeOid(semesterTypeCode);
				    		}
				    	}else{
				    		semester.setSemesterTypeOid(null);
				    	}
					    semesterService.update(semester);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "redirect:/semester/getList.do";
	}
	
	@RequestMapping(value = "/getDelete.do", method=RequestMethod.GET)
	public String getDelete(WebRequest request){
		String semesterOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(semesterOid)){
			SemesterT semester = semesterService.findById(SemesterT.class, Long.valueOf(semesterOid));
			if(semester != null){
				//
			}
		}
		return "redirect:/semester/getList.do";
	}
	
}
