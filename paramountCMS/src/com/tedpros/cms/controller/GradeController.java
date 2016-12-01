package com.tedpros.cms.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.tedpros.cms.entity.CodeValueT;
import com.tedpros.cms.entity.CourseOfferT;
import com.tedpros.cms.entity.EnrollmentT;
import com.tedpros.cms.entity.FacultyT;
import com.tedpros.cms.entity.GradeT;
import com.tedpros.cms.entity.StudentT;
import com.tedpros.cms.service.CourseOfferService;
import com.tedpros.cms.service.EnrollmentService;
import com.tedpros.cms.service.GradeService;

@Controller
@RequestMapping("/grade/*")
public class GradeController extends TopController{
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private CourseOfferService courseOfferService;
	
	@RequestMapping(value = "/getList.do", method=RequestMethod.GET)
	public String getList(WebRequest request){
		FacultyT faculty = (FacultyT) request.getAttribute(LOGIN_USER, WebRequest.SCOPE_SESSION);
		if(faculty != null){
			List<CourseOfferT> courseOfferList = courseOfferService.findByFacultyOid(faculty.getObjectid());
			request.setAttribute("courseOfferList", courseOfferList, WebRequest.SCOPE_REQUEST);			
		}
		return "grade.list";
	}
	
	@RequestMapping(value = "/getResultList.do",method=RequestMethod.GET)
	@ResponseBody
	public String getResultList(WebRequest request){
		String assignmentOid = request.getParameter("assignmentOid");
		String courseOfferOid = request.getParameter("courseOfferOid");
		Map<String, Object> jsonMap = new HashMap<>();
		if(StringUtils.isNotBlank(assignmentOid)){
			List<GradeT> gradeList = gradeService.findByAssingmentOid(Long.valueOf(assignmentOid));
			List<Long> excludeLists = getObjectIds(gradeList);
			List<EnrollmentT> enrollmentList = null;
			if(excludeLists != null && !excludeLists.isEmpty()){
				enrollmentList = enrollmentService.findByCourseOfferOidExcludeStudentOids(Long.valueOf(courseOfferOid), excludeLists);
			}else{
				enrollmentList = enrollmentService.findByCourseOfferOid(Long.valueOf(courseOfferOid));
			}
			jsonMap.put("recordsTotal", enrollmentList.size());
			jsonMap.put("recordsFiltered", enrollmentList.size());
			jsonMap.put("data", getStudentList(enrollmentList));
		}else{
			jsonMap.put("recordsTotal", 0L);
			jsonMap.put("recordsFiltered", 0L);
			jsonMap.put("data", new ArrayList<>());
		}
		
		
		String jsonString = new JSONObject(jsonMap).toJSONString();
		return jsonString;
	}
	
	private List<Long> getObjectIds(List<GradeT> gradeList){
		List<Long> oIds = new ArrayList<>();
		if(gradeList != null && !gradeList.isEmpty()){
			for (GradeT grade : gradeList) {
				oIds.add(grade.getEnrollmentOid().getStudentOid().getObjectid());
			}
		}
		return oIds;
	}

	private List<Object> getStudentList(List<EnrollmentT> enrollmentList){
		List<Object> cv = new ArrayList<>();
		for (EnrollmentT enrollment : enrollmentList) {
			Map<String, Object> valueMap = new HashMap<>();
			valueMap.put("firstName", enrollment.getStudentOid().getFirstName());
			valueMap.put("lastName", enrollment.getStudentOid().getLastName());
			valueMap.put("gender", enrollment.getStudentOid().getGenderOid().getShortDescription());
			valueMap.put("type", enrollment.getStudentOid().getStudentTypeOid().getShortDescription());
			valueMap.put("status", enrollment.getStudentOid().getStudentStatusOid().getShortDescription());
			valueMap.put("classStatus", enrollment.getStudentOid().getClassStatusOid().getShortDescription());
			valueMap.put("objectid", enrollment.getObjectid());
			cv.add(valueMap);
		}
		return cv;
	}
	
	@RequestMapping(value = "/getView.do", method=RequestMethod.GET)
	public String getView(WebRequest request){
		String courseOfferOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(courseOfferOid)){
			CourseOfferT courseOffer = null;
			List<EnrollmentT> enrollmentList = enrollmentService.findByCourseOfferOid(Long.valueOf(courseOfferOid));
			if(enrollmentList != null && !enrollmentList.isEmpty()){
				courseOffer = enrollmentList.get(0).getCourseOfferOid();
			}else{
				courseOffer = enrollmentService.findById(CourseOfferT.class, Long.valueOf(courseOfferOid));
			}
			request.setAttribute("courseOffer", courseOffer, WebRequest.SCOPE_REQUEST);
			request.setAttribute("enrollmentList", enrollmentList, WebRequest.SCOPE_REQUEST);
		}
		return "grade.view";
	}
	
	@RequestMapping(value = "/getViewAssignments.do", method=RequestMethod.GET)
	public String getViewAssignments(WebRequest request){
		String courseOfferOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(courseOfferOid)){
			List<CodeValueT> assignmentList = gradeService.findAssignmentByCourseOfferOid(Long.valueOf(courseOfferOid));
			request.setAttribute("assignmentList", assignmentList, WebRequest.SCOPE_REQUEST);
			CourseOfferT courseOffer = courseOfferService.findById(CourseOfferT.class, Long.valueOf(courseOfferOid));
			request.setAttribute("courseOffer", courseOffer, WebRequest.SCOPE_REQUEST);
		}
		return "grade.viewAssignments";
	}
	
	@RequestMapping(value = "/getViewStudentGrade.do", method=RequestMethod.GET)
	public String getViewStudentGrade(WebRequest request){
		String assignmentOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(assignmentOid)){
			List<GradeT> gradeList = gradeService.findByAssingmentOid(Long.valueOf(assignmentOid));
			request.setAttribute("gradeList", gradeList, WebRequest.SCOPE_REQUEST);
			CodeValueT assignemt = null;
			if(gradeList != null && !gradeList.isEmpty()){
				assignemt = gradeList.get(0).getAssignmentOid();
			}else{
				assignemt = gradeService.findById(CodeValueT.class, Long.valueOf(assignmentOid));
			}
			request.setAttribute("assignemt", assignemt, WebRequest.SCOPE_REQUEST);
		}
		return "grade.viewStudents";
	}
	
	@RequestMapping(value = "/getViewStudent.do", method=RequestMethod.GET)
	public String getViewStudent(WebRequest request){
		return "grade.viewStudent";
	}
	
	@RequestMapping(value = "/postGrade.do", method=RequestMethod.POST)
	public String postGrade(WebRequest request, HttpServletRequest httpRequest){
		Long courseOfferOid = null;
		try {
			String[] enrollmentOids = request.getParameterValues("enrollOids");
			if(enrollmentOids != null && enrollmentOids.length > 0){
				List<Long> objectids = new ArrayList<>();
				objectids.addAll(Arrays.asList(enrollmentOids).stream().map(Long::valueOf).collect(Collectors.toList()));
				List<EnrollmentT> enrollmentList = enrollmentService.findByObjectIds(objectids);
				for (EnrollmentT enrollment : enrollmentList) {
					GradeT grade = new GradeT();
					String actualPoint = request.getParameter("actualPoint");
			    	if(StringUtils.isNotBlank(actualPoint) && StringUtils.isNumeric(actualPoint)){
			    		grade.setActualPoint(new BigDecimal(actualPoint).setScale(3, RoundingMode.HALF_UP));
			    	}
					String assignmentOid = request.getParameter("assignmentOid");
			    	if(StringUtils.isNotBlank(assignmentOid)){
			    		CodeValueT assignmentCode = enrollmentService.findById(CodeValueT.class, Long.valueOf(assignmentOid));
			    		if(assignmentCode != null){
			    			grade.setAssignmentOid(assignmentCode);;
			    		}
			    	}
			    	
			    	String gradeTypeOid = request.getParameter("gradeTypeOid");
			    	if(StringUtils.isNotBlank(gradeTypeOid)){
			    		CodeValueT gradeTypeCode = enrollmentService.findById(CodeValueT.class, Long.valueOf(gradeTypeOid));
			    		if(gradeTypeCode != null){
			    			grade.setGradeTypeOid(gradeTypeCode);
			    			grade.setLetterGrade(gradeTypeCode.getCode());
			    		}
			    	}
			    	grade.setEnrollmentOid(enrollment);
			    	courseOfferOid = enrollment.getCourseOfferOid().getObjectid();
			    	gradeService.persist(grade);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/grade/getView.do?objectid="+courseOfferOid;
	}
	
	@RequestMapping(value = "/postComments.do", method=RequestMethod.POST)
	public String postComments(WebRequest request){
		String courseOfferOid= request.getParameter("objectid");
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/grade/getView.do?objectid="+courseOfferOid;
	}
	
}
