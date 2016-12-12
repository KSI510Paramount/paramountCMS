package com.tedpros.cms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.tedpros.cms.entity.AttendanceT;
import com.tedpros.cms.entity.CodeValueT;
import com.tedpros.cms.entity.CourseOfferT;
import com.tedpros.cms.entity.EnrollmentT;
import com.tedpros.cms.entity.FacultyT;
import com.tedpros.cms.service.AttendanceService;
import com.tedpros.cms.service.CourseOfferService;
import com.tedpros.cms.service.EnrollmentService;

@Controller
@RequestMapping("/attendance/*")
public class AttendanceController extends TopController{
	
	@Autowired
	private AttendanceService attendanceService;
	
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
		return "attendance.list";
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
		return "attendance.view";
	}
	
	@RequestMapping(value = "/getEditAttendance.do", method=RequestMethod.GET)
	public String getEditAttendance(WebRequest request){
		String attendaceOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(attendaceOid)){
			AttendanceT attendance = attendanceService.findById(AttendanceT.class, Long.valueOf(attendaceOid));
			if(attendance != null){
				request.setAttribute("attendance", attendance, WebRequest.SCOPE_REQUEST);			
				request.setAttribute("courseOffer", attendance.getEnrollmentOid().getCourseOfferOid(), WebRequest.SCOPE_REQUEST);				
			}
		}
		return "attendance.edit";
	}
	
	@RequestMapping(value = "/postEditAttendance.do", method=RequestMethod.POST)
	public String postEditAttendance(WebRequest request, HttpServletRequest httpRequest){
		Long courseOfferOid = null;
		String attendaceOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(attendaceOid)){
			AttendanceT attendance = attendanceService.findById(AttendanceT.class, Long.valueOf(attendaceOid));
			if(attendance != null){
				getDataBinder(request, attendance);
				String attenTypeOid = request.getParameter("attenTypeOid");
		    	if(StringUtils.isNotBlank(attenTypeOid)){
		    		CodeValueT attenTypeCode = enrollmentService.findById(CodeValueT.class, Long.valueOf(attenTypeOid));
		    		if(attenTypeCode != null){
		    			attendance.setAttenTypeOid(attenTypeCode);
		    		}
		    	}
		    	courseOfferOid = attendance.getEnrollmentOid().getCourseOfferOid().getObjectid();
		    	attendanceService.update(attendance);
			}
		}
		return "redirect:/attendance/getViewStudent.do?objectid="+courseOfferOid;
	}
	
	@RequestMapping(value = "/getViewStudent.do", method=RequestMethod.GET)
	public String getViewStudent(WebRequest request){
		String courseOfferOid= request.getParameter("objectid");
		request.setAttribute("courseOfferOid", request.getParameter("courseOfferOid"), WebRequest.SCOPE_REQUEST);
		if(StringUtils.isNotBlank(courseOfferOid)){
			List<AttendanceT> attendanceList = attendanceService.findByCourseOfferOid(Long.valueOf(courseOfferOid));
			request.setAttribute("attendanceList", attendanceList, WebRequest.SCOPE_REQUEST);
			CourseOfferT courseOffer = null;
			if(attendanceList != null && !attendanceList.isEmpty()){
				courseOffer = attendanceList.get(0).getEnrollmentOid().getCourseOfferOid();
			}else{
				courseOffer = attendanceService.findById(CourseOfferT.class, Long.valueOf(courseOfferOid));
			}
			request.setAttribute("courseOffer", courseOffer, WebRequest.SCOPE_REQUEST);
		}
		return "attendance.viewStudent";
	}
	
	@RequestMapping(value = "/postAttendance.do", method=RequestMethod.POST)
	public String postAttendance(WebRequest request, HttpServletRequest httpRequest){
		Long courseOfferOid = null;
		try {
			String[] enrollmentOids = request.getParameterValues("enrollOids");
			if(enrollmentOids != null && enrollmentOids.length > 0){
				List<Long> objectids = new ArrayList<>();
				objectids.addAll(Arrays.asList(enrollmentOids).stream().map(Long::valueOf).collect(Collectors.toList()));
				List<EnrollmentT> enrollmentList = enrollmentService.findByObjectIds(objectids);
				for (EnrollmentT enrollment : enrollmentList) {
					AttendanceT attendance = new AttendanceT();
					getDataBinder(request, attendance);
					String attenTypeOid = request.getParameter("attenTypeOid");
			    	if(StringUtils.isNotBlank(attenTypeOid)){
			    		CodeValueT attenTypeCode = enrollmentService.findById(CodeValueT.class, Long.valueOf(attenTypeOid));
			    		if(attenTypeCode != null){
			    			attendance.setAttenTypeOid(attenTypeCode);
			    		}
			    	}
			    	attendance.setEnrollmentOid(enrollment);
			    	courseOfferOid = enrollment.getCourseOfferOid().getObjectid();
			    	attendanceService.persist(attendance);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/attendance/getView.do?objectid="+courseOfferOid;
	}
	
	@RequestMapping(value = "/postComments.do", method=RequestMethod.POST)
	public String postComments(WebRequest request){
		String courseOfferOid= request.getParameter("objectid");
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/attendance/getView.do?objectid="+courseOfferOid;
	}
	
}
