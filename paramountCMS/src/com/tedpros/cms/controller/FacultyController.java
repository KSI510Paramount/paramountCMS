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
import com.tedpros.cms.entity.FacultyT;
import com.tedpros.cms.entity.UserT;
import com.tedpros.cms.service.CodeValueService;
import com.tedpros.cms.service.FacultyService;

@Controller
@RequestMapping("/faculty/*")
public class FacultyController extends TopController{
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private CodeValueService codeValueService;
	
	@RequestMapping(value = "/getList.do", method=RequestMethod.GET)
	public String getList(WebRequest request){
		List<FacultyT> facultyList = facultyService.findAll();
		request.setAttribute("facultyList", facultyList, WebRequest.SCOPE_REQUEST);
		return "faculty.list";
	}
	
	@RequestMapping(value = "/getAdd.do", method=RequestMethod.GET)
	public String getAdd(WebRequest request){
		return "faculty.add";
	}
	
	@RequestMapping(value = "/postAdd.do", method=RequestMethod.POST)
	public String postAdd(WebRequest request){
		try {
	    	FacultyT faculty = new FacultyT();
	    	getDataBinder(request, faculty);
	    	String genderOid = request.getParameter("genderOid");
	    	if(StringUtils.isNotBlank(genderOid)){
	    		CodeValueT genderCode = codeValueService.findById(CodeValueT.class, Long.valueOf(genderOid));
	    		if(genderCode != null){
	    			faculty.setGenderOid(genderCode);
	    		}
	    	}
	    	String facultyTypeOid = request.getParameter("facultyTypeOid");
	    	if(StringUtils.isNotBlank(facultyTypeOid)){
	    		CodeValueT facultyTypeCode = codeValueService.findById(CodeValueT.class, Long.valueOf(facultyTypeOid));
	    		if(facultyTypeCode != null){
	    			faculty.setFacultyTypeOid(facultyTypeCode);
	    		}
	    	}
	    	
	    	String facultyStatusOid = request.getParameter("facultyStatusOid");
	    	if(StringUtils.isNotBlank(facultyStatusOid)){
	    		CodeValueT facultyStatusCode = codeValueService.findById(CodeValueT.class, Long.valueOf(facultyStatusOid));
	    		if(facultyStatusCode != null){
	    			faculty.setFacultyStatusOid(facultyStatusCode);
	    		}
	    	}
	    	
	    	String classStatusOid = request.getParameter("classStatusOid");
	    	if(StringUtils.isNotBlank(classStatusOid)){
	    		CodeValueT classStatusCode = codeValueService.findById(CodeValueT.class, Long.valueOf(classStatusOid));
	    		if(classStatusCode != null){
	    			faculty.setClassStatusOid(classStatusCode);
	    		}
	    	}
	    	UserT user = new UserT();
	    	getDataBinder(request, user);
	    	String activeFlag = request.getParameter("activeFlag");
	    	if(StringUtils.isNotBlank(activeFlag)){
	    		user.setActiveFlag("Y");
	    	}else{
	    		user.setActiveFlag("N");
	    	}
	    	String roleOid = request.getParameter("roleOid");
	    	if(StringUtils.isNotBlank(roleOid)){
	    		CodeValueT roleCode = codeValueService.findById(CodeValueT.class, Long.valueOf(roleOid));
	    		if(roleCode != null){
	    			user.setRoleOid(roleCode);
	    		}
	    	}
	    	user = facultyService.persist(user);
	    	//Active Flag
	    	faculty.setUserOid(user);
		    facultyService.persist(faculty);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/faculty/getList.do";
	}
	
	@RequestMapping(value = "/getEdit.do", method=RequestMethod.GET)
	public String getEdit(WebRequest request){
		String facultyOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(facultyOid)){
			FacultyT faculty = facultyService.findById(FacultyT.class, Long.valueOf(facultyOid));
			if(faculty != null){
				request.setAttribute("faculty", faculty, WebRequest.SCOPE_REQUEST);
			}
		}
		return "faculty.edit";
	}
	
	@RequestMapping(value = "/postEdit.do", method=RequestMethod.POST)
	public String postEdit(WebRequest request, HttpServletRequest httpRequest){
		 try {
			 	String facultyOid= request.getParameter("objectid");
				if(StringUtils.isNotBlank(facultyOid)){
					FacultyT faculty = facultyService.findById(FacultyT.class, Long.valueOf(facultyOid));
					if(faculty != null){
				    	getDataBinder(request, faculty);
				    	String genderOid = request.getParameter("genderOid");
				    	if(StringUtils.isNotBlank(genderOid)){
				    		CodeValueT genderCode = codeValueService.findById(CodeValueT.class, Long.valueOf(genderOid));
				    		if(genderCode != null){
				    			faculty.setGenderOid(genderCode);
				    		}
				    	}else{
				    		faculty.setGenderOid(null);
				    	}
				    	String facultyTypeOid = request.getParameter("facultyTypeOid");
				    	if(StringUtils.isNotBlank(facultyTypeOid)){
				    		CodeValueT facultyTypeCode = codeValueService.findById(CodeValueT.class, Long.valueOf(facultyTypeOid));
				    		if(facultyTypeCode != null){
				    			faculty.setFacultyTypeOid(facultyTypeCode);
				    		}
				    	}else{
				    		faculty.setFacultyTypeOid(null);
				    	}
				    	
				    	String facultyStatusOid = request.getParameter("facultyStatusOid");
				    	if(StringUtils.isNotBlank(facultyStatusOid)){
				    		CodeValueT facultyStatusCode = codeValueService.findById(CodeValueT.class, Long.valueOf(facultyStatusOid));
				    		if(facultyStatusCode != null){
				    			faculty.setFacultyStatusOid(facultyStatusCode);
				    		}
				    	}else{
				    		faculty.setFacultyStatusOid(null);
				    	}
				    	
				    	String classStatusOid = request.getParameter("classStatusOid");
				    	if(StringUtils.isNotBlank(classStatusOid)){
				    		CodeValueT classStatusCode = codeValueService.findById(CodeValueT.class, Long.valueOf(classStatusOid));
				    		if(classStatusCode != null){
				    			faculty.setClassStatusOid(classStatusCode);
				    		}
				    	}else{
				    		faculty.setClassStatusOid(null);
				    	}
				    	UserT user = faculty.getUserOid();
				    	getDataBinder(request, user);
				    	String activeFlag = request.getParameter("activeFlag");
				    	if(StringUtils.isNotBlank(activeFlag)){
				    		user.setActiveFlag("Y");
				    	}else{
				    		user.setActiveFlag("N");
				    	}
				    	String roleOid = request.getParameter("roleOid");
				    	if(StringUtils.isNotBlank(roleOid)){
				    		CodeValueT roleCode = codeValueService.findById(CodeValueT.class, Long.valueOf(roleOid));
				    		if(roleCode != null){
				    			user.setRoleOid(roleCode);
				    		}
				    	}
				    	user = facultyService.update(user); 
				    	//Active Flag
				    	faculty.setUserOid(user);
					    facultyService.update(faculty);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "redirect:/faculty/getList.do";
	}
	
	@RequestMapping(value = "/getDelete.do", method=RequestMethod.GET)
	public String getDelete(WebRequest request){
		String facultyOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(facultyOid)){
			FacultyT faculty = facultyService.findById(FacultyT.class, Long.valueOf(facultyOid));
			if(faculty != null){
				//
			}
		}
		return "redirect:/faculty/getList.do";
	}
	
}
