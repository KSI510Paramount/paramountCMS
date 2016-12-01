package com.tedpros.cms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.tedpros.cms.entity.CourseOfferT;
import com.tedpros.cms.entity.EnrollmentT;
import com.tedpros.cms.entity.FacultyT;
import com.tedpros.cms.entity.StudentT;
import com.tedpros.cms.service.CourseOfferService;
import com.tedpros.cms.service.EnrollmentService;
import com.tedpros.cms.service.StudentService;

@Controller
@RequestMapping("/enroll/*")
public class EnrollmentController extends TopController{
	
	@Autowired
	private EnrollmentService enrollService;
	
	@Autowired
	private CourseOfferService courseOfferService;
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/getList.do", method=RequestMethod.GET)
	public String getList(WebRequest request){
		FacultyT faculty = (FacultyT) request.getAttribute(LOGIN_USER, WebRequest.SCOPE_SESSION);
		if(faculty != null){
			List<CourseOfferT> courseOfferList = courseOfferService.findByFacultyOid(faculty.getObjectid());
			request.setAttribute("courseOfferList", courseOfferList, WebRequest.SCOPE_REQUEST);			
		}
		return "enroll.list";
	}
	
	@RequestMapping(value = "/getView.do", method=RequestMethod.GET)
	public String getView(WebRequest request){
		String courseOfferOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(courseOfferOid)){
			CourseOfferT courseOffer = null;
			List<EnrollmentT> enrollmentList = enrollService.findByCourseOfferOid(Long.valueOf(courseOfferOid));
			if(enrollmentList != null && !enrollmentList.isEmpty()){
				courseOffer = enrollmentList.get(0).getCourseOfferOid();
			}else{
				courseOffer = enrollService.findById(CourseOfferT.class, Long.valueOf(courseOfferOid));
			}
			request.setAttribute("courseOffer", courseOffer, WebRequest.SCOPE_REQUEST);
			request.setAttribute("enrollmentList", enrollmentList, WebRequest.SCOPE_REQUEST);
		}
		return "enroll.view";
	}
	
	@RequestMapping(value = "/getEnroll.do", method=RequestMethod.GET)
	public String getEnroll(WebRequest request){
		String courseOfferOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(courseOfferOid)){
			CourseOfferT courseOffer = enrollService.findById(CourseOfferT.class, Long.valueOf(courseOfferOid));
			if(courseOffer != null){
				request.setAttribute("courseOffer", courseOffer, WebRequest.SCOPE_REQUEST);
			}
			List<EnrollmentT>  enrollmentedList = enrollService.findByCourseOfferOid(courseOffer.getObjectid());
			List<Long> excludeLists = getObjectIds(enrollmentedList);
			List<StudentT> studentList = null;
			if(excludeLists != null && !excludeLists.isEmpty()){
				studentList = studentService.findAllWithExcludesStudents(excludeLists);
			}else{
				studentList = studentService.findAll();
			}
			request.setAttribute("studentList", studentList, WebRequest.SCOPE_REQUEST);
		}
		return "enroll.add";
	}
	
	private List<Long> getObjectIds(List<EnrollmentT> enrollmentList){
		List<Long> oIds = new ArrayList<>();
		if(enrollmentList != null && !enrollmentList.isEmpty()){
			for (EnrollmentT enrollment : enrollmentList) {
				oIds.add(enrollment.getStudentOid().getObjectid());
			}
		}
		return oIds;
	}
	
	@RequestMapping(value = "/postEnroll.do", method=RequestMethod.POST)
	public String postEnroll(WebRequest request, HttpServletRequest httpRequest){
		String courseOfferOid= request.getParameter("objectid");
		try {
			
			if(StringUtils.isNotBlank(courseOfferOid)){
				EnrollmentT enrollment = new EnrollmentT();
				enrollment.setEnrollmentDate(new Date());
				CourseOfferT courseOffer = enrollService.findById(CourseOfferT.class, Long.valueOf(courseOfferOid));
				enrollment.setCourseOfferOid(courseOffer);
				String studentOid= request.getParameter("studentOid");
				if(StringUtils.isNotBlank(studentOid)){
					StudentT student = enrollService.findById(StudentT.class, Long.valueOf(studentOid));
					enrollment.setStudentOid(student);
				}
				enrollService.persist(enrollment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/enroll/getView.do?objectid="+courseOfferOid;
	}
	
	@RequestMapping(value = "/getEditEnrollment.do", method=RequestMethod.GET)
	public String getEditEnrollment(WebRequest request){
		String enrollmentOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(enrollmentOid)){
			EnrollmentT enrolled = enrollService.findById(EnrollmentT.class, Long.valueOf(enrollmentOid));
			if(enrolled != null){
				request.setAttribute("enrolled", enrolled, WebRequest.SCOPE_REQUEST);
			}
			List<EnrollmentT>  enrollmentedList = enrollService.findByCourseOfferOid(enrolled.getObjectid());
			List<Long> excludeLists = getObjectIds(enrollmentedList);
			Iterator<Long> iterator = excludeLists.iterator(); 
			while ( iterator.hasNext()) {
				Long oid = iterator.next();
				if(oid.compareTo(enrolled.getStudentOid().getObjectid()) == 0){
					iterator.remove();
				}
				
			}
			List<StudentT> studentList = null;
			if(excludeLists != null && !excludeLists.isEmpty()){
				studentList = studentService.findAllWithExcludesStudents(excludeLists);
			}else{
				studentList = studentService.findAll();
			}
			request.setAttribute("studentList", studentList, WebRequest.SCOPE_REQUEST);
		}
		return "enroll.edit";
	}
	
	@RequestMapping(value = "/postEditEnrollment.do", method=RequestMethod.POST)
	public String postEditEnrollment(WebRequest request, HttpServletRequest httpRequest){
		Long courseOfferOid= null;
		try {
			String enrollmentOid = request.getParameter("objectid");
			if(StringUtils.isNotBlank(enrollmentOid)){
				EnrollmentT enrollment = enrollService.findById(EnrollmentT.class, Long.valueOf(enrollmentOid));
				String studentOid= request.getParameter("studentOid");
				if(StringUtils.isNotBlank(studentOid)){
					StudentT student = enrollService.findById(StudentT.class, Long.valueOf(studentOid));
					enrollment.setStudentOid(student);
				}
				courseOfferOid = enrollment.getCourseOfferOid().getObjectid();
				enrollService.update(enrollment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/enroll/getView.do?objectid="+courseOfferOid;
	}
	
	@RequestMapping(value = "/getDeletEnrollment.do", method=RequestMethod.GET)
	public String getDeletEnrollment(WebRequest request){
		String courseOfferOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(courseOfferOid)){
			EnrollmentT enroll = enrollService.findById(EnrollmentT.class, Long.valueOf(courseOfferOid));
			if(enroll != null){
				enrollService.delete(enroll);
			}
		}
		return "redirect:/enroll/getView.do?objectid="+courseOfferOid;
	}
}
