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
import com.tedpros.cms.entity.StudentT;
import com.tedpros.cms.service.CodeValueService;
import com.tedpros.cms.service.StudentService;

@Controller
@RequestMapping("/student/*")
public class StudentController extends TopController{

	public static Integer studentCount = 0;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CodeValueService codeValueService;
	
	@RequestMapping(value = "/getList.do", method=RequestMethod.GET)
	public String getList(WebRequest request){
		List<StudentT> studentList = studentService.findAll();
		if((studentList != null && !studentList.isEmpty()) && StudentController.studentCount.intValue() == 0){
			StudentController.studentCount = Integer.valueOf(studentList.size());
		}
		request.setAttribute("studentList", studentList, WebRequest.SCOPE_REQUEST);
		return "student.list";
	}
	
	@RequestMapping(value = "/getAdd.do", method=RequestMethod.GET)
	public String getAdd(WebRequest request){
		return "student.add";
	}
	
	@RequestMapping(value = "/postAdd.do", method=RequestMethod.POST)
	public String postAdd(WebRequest request){
	    try {
	    	StudentT student = new StudentT();
	    	getDataBinder(request, student);
	    	String genderOid = request.getParameter("genderOid");
	    	if(StringUtils.isNotBlank(genderOid)){
	    		CodeValueT genderCode = codeValueService.findById(CodeValueT.class, Long.valueOf(genderOid));
	    		if(genderCode != null){
	    			student.setGenderOid(genderCode);
	    		}
	    	}
	    	
	    	String studentTypeOid = request.getParameter("studentTypeOid");
	    	if(StringUtils.isNotBlank(studentTypeOid)){
	    		CodeValueT studentTypeCode = codeValueService.findById(CodeValueT.class, Long.valueOf(studentTypeOid));
	    		if(studentTypeCode != null){
	    			student.setStudentTypeOid(studentTypeCode);
	    		}
	    	}
	    	String studentStatusOid = request.getParameter("studentStatusOid");
	    	if(StringUtils.isNotBlank(studentStatusOid)){
	    		CodeValueT studentStatusCode = codeValueService.findById(CodeValueT.class, Long.valueOf(studentStatusOid));
	    		if(studentStatusCode != null){
	    			student.setStudentStatusOid(studentStatusCode);
	    		}
	    	}
	    	String classStatusOid = request.getParameter("classStatusOid");
	    	if(StringUtils.isNotBlank(classStatusOid)){
	    		CodeValueT classStatusCode = codeValueService.findById(CodeValueT.class, Long.valueOf(classStatusOid));
	    		if(classStatusCode != null){
	    			student.setClassStatusOid(classStatusCode);
	    		}
	    	}
	    	student.setStudentId(null);
		    studentService.persist(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/student/getList.do";
	}
	
	@RequestMapping(value = "/getEdit.do", method=RequestMethod.GET)
	public String getEdit(WebRequest request){
		String studentOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(studentOid)){
			StudentT student = studentService.findById(StudentT.class, Long.valueOf(studentOid));
			if(student != null){
				request.setAttribute("student", student, WebRequest.SCOPE_REQUEST);
			}
		}
		return "student.edit";
	}
	
	@RequestMapping(value = "/postEdit.do", method=RequestMethod.POST)
	public String postEdit(WebRequest request, HttpServletRequest httpRequest){
		 try {
			 String studentOid= request.getParameter("objectid");
				if(StringUtils.isNotBlank(studentOid)){
					StudentT student = studentService.findById(StudentT.class, Long.valueOf(studentOid));
					if(student != null){
				    	getDataBinder(request, student);
				    	String genderOid = request.getParameter("genderOid");
				    	if(StringUtils.isNotBlank(genderOid)){
				    		CodeValueT genderCode = codeValueService.findById(CodeValueT.class, Long.valueOf(genderOid));
				    		if(genderCode != null){
				    			student.setGenderOid(genderCode);
				    		}
				    	}else{
				    		student.setGenderOid(null);
				    	}
				    	
				    	String studentTypeOid = request.getParameter("studentTypeOid");
				    	if(StringUtils.isNotBlank(studentTypeOid)){
				    		CodeValueT studentTypeCode = codeValueService.findById(CodeValueT.class, Long.valueOf(studentTypeOid));
				    		if(studentTypeCode != null){
				    			student.setStudentTypeOid(studentTypeCode);
				    		}
				    	}else{
				    		student.setStudentTypeOid(null);
				    	}
				    	
				    	String studentStatusOid = request.getParameter("studentStatusOid");
				    	if(StringUtils.isNotBlank(studentStatusOid)){
				    		CodeValueT studentStatusCode = codeValueService.findById(CodeValueT.class, Long.valueOf(studentStatusOid));
				    		if(studentStatusCode != null){
				    			student.setStudentStatusOid(studentStatusCode);
				    		}
				    	}else{
				    		student.setStudentStatusOid(null);
				    	}
				    	
				    	String classStatusOid = request.getParameter("classStatusOid");
				    	if(StringUtils.isNotBlank(classStatusOid)){
				    		CodeValueT classStatusCode = codeValueService.findById(CodeValueT.class, Long.valueOf(classStatusOid));
				    		if(classStatusCode != null){
				    			student.setClassStatusOid(classStatusCode);
				    		}
				    	}else{
				    		student.setClassStatusOid(null);
				    	}
				    	
					    studentService.update(student);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "redirect:/student/getList.do";
	}
	
	@RequestMapping(value = "/getDelete.do", method=RequestMethod.GET)
	public String getDelete(WebRequest request){
		String courseOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(courseOid)){
			StudentT course = studentService.findById(StudentT.class, Long.valueOf(courseOid));
			if(course != null){
				//studentService.delete(course);
			}
		}
		return "redirect:/student/getList.do";
	}
}
