package com.tedpros.cms.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.tedpros.cms.entity.CodeValueT;
import com.tedpros.cms.entity.CourseOfferT;
import com.tedpros.cms.entity.CourseT;
import com.tedpros.cms.entity.FacultyT;
import com.tedpros.cms.entity.SemesterT;
import com.tedpros.cms.service.CodeValueService;
import com.tedpros.cms.service.CourseOfferService;
import com.tedpros.cms.service.CourseService;
import com.tedpros.cms.service.SemesterService;

@Controller
@RequestMapping("/semester/*")
public class SemesterController extends TopController{
	
	@Autowired
	private SemesterService semesterService;
	
	@Autowired
	private CodeValueService codeValueService;
	
	@Autowired
	private CourseOfferService courseOfferService;
	
	@Autowired
	private CourseService courseService;
	
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
	
	@RequestMapping(value = "/getView.do", method=RequestMethod.GET)
	public String getView(WebRequest request){
		String semesterOid= request.getParameter("objectid");
		FacultyT faculty = (FacultyT) request.getAttribute(LOGIN_USER, WebRequest.SCOPE_SESSION);
		if(StringUtils.isNotBlank(semesterOid)){
			SemesterT semester = semesterService.findById(SemesterT.class, Long.valueOf(semesterOid));
			if(semester != null){
				request.setAttribute("semester", semester, WebRequest.SCOPE_REQUEST);
			}
			List<CourseOfferT>  courseOfferList = courseOfferService.findByFacultyOidAndSemesterOid(faculty.getObjectid(), semester.getObjectid());
			request.setAttribute("courseOfferList", courseOfferList, WebRequest.SCOPE_REQUEST);
		}
		return "semester.view";
	}
	
	@RequestMapping(value = "/getDeleteCourseOffer.do", method=RequestMethod.GET)
	public String getDelete(WebRequest request){
		String courseOfferOid= request.getParameter("objectid");
		Long semesterOid = null;
		if(StringUtils.isNotBlank(courseOfferOid)){
			CourseOfferT courseOffer = semesterService.findById(CourseOfferT.class, Long.valueOf(courseOfferOid));
			if(courseOffer != null){
				semesterOid = courseOffer.getSemesterOid().getObjectid();
				courseOfferService.delete(courseOffer);
			}
		}
		return "redirect:/semester/getView.do?objectid="+semesterOid;
	}
	
	@RequestMapping(value = "/getAddCourseOffer.do", method=RequestMethod.GET)
	public String getAddCourseOffer(WebRequest request){
		String semesterOid= request.getParameter("objectid");
		FacultyT faculty = (FacultyT) request.getAttribute(LOGIN_USER, WebRequest.SCOPE_SESSION);
		if(StringUtils.isNotBlank(semesterOid)){
			SemesterT semester = semesterService.findById(SemesterT.class, Long.valueOf(semesterOid));
			if(semester != null){
				request.setAttribute("semester", semester, WebRequest.SCOPE_REQUEST);
			}
			List<CourseOfferT>  courseOfferList = courseOfferService.findByFacultyOidAndSemesterOid(faculty.getObjectid(), semester.getObjectid());
			List<Long> excludeLists = getObjectIds(courseOfferList);
			List<CourseT> courseList = null;
			if(excludeLists != null && !excludeLists.isEmpty()){
				courseList = courseService.findAllWithExcludesCourse(excludeLists);
			}else{
				courseList = courseService.findAll();
			}
			request.setAttribute("courseList", courseList, WebRequest.SCOPE_REQUEST);
		}
		return "semester.addCourseOffer";
	}
	
	private List<Long> getObjectIds(List<CourseOfferT> courseOfferList){
		List<Long> oIds = new ArrayList<>();
		if(courseOfferList != null && !courseOfferList.isEmpty()){
			for (CourseOfferT courseOffer : courseOfferList) {
				oIds.add(courseOffer.getCourseOid().getObjectid());
			}
		}
		return oIds;
	}
	
	@RequestMapping(value = "/postAddCourseOffer.do", method=RequestMethod.POST)
	public String postAddCourseOffer(WebRequest request){
		String courseOids = request.getParameter("courseOids");
		String semesterOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(courseOids)) {
			CourseT course = courseService.findById(CourseT.class, Long.valueOf(courseOids));
			if(course != null){
				FacultyT faculty = (FacultyT) request.getAttribute(LOGIN_USER, WebRequest.SCOPE_SESSION);
				CourseOfferT courseOffer = new CourseOfferT();
				courseOffer.setCourseOid(course);
				courseOffer.setFacultyOid(faculty);
				if(StringUtils.isNotBlank(semesterOid)){
					SemesterT semester = semesterService.findById(SemesterT.class, Long.valueOf(semesterOid));
					courseOffer.setSemesterOid(semester);
					courseOffer.setStartDate(semester.getSemStartDate());
					courseOffer.setEndDate(semester.getSemEndDate());
				}
				String locationOid= request.getParameter("locationOid");
				if(StringUtils.isNotBlank(locationOid)){
		    		CodeValueT locationCode = codeValueService.findById(CodeValueT.class, Long.valueOf(locationOid));
		    		if(locationCode != null){
		    			courseOffer.setLocationOid(locationCode);
		    		}
		    	}
				courseOfferService.persist(courseOffer);
			}
		}
		return "redirect:/semester/getView.do?objectid="+semesterOid;
	}
	
	@RequestMapping(value = "/getEditCourseOffer.do", method=RequestMethod.GET)
	public String getEditCourseOffer(WebRequest request){
		String courseOffierOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(courseOffierOid)){
			CourseOfferT courseOffer = courseOfferService.findById(CourseOfferT.class, Long.valueOf(courseOffierOid));
			request.setAttribute("courseOffer", courseOffer, WebRequest.SCOPE_REQUEST);
			
			List<CourseOfferT>  courseOfferList = courseOfferService.findByFacultyOidAndSemesterOid(courseOffer.getFacultyOid().getObjectid(), courseOffer.getSemesterOid().getObjectid());
			List<Long> excludeLists = getObjectIds(courseOfferList);
			Iterator<Long> iterator = excludeLists.iterator();
			while ( iterator.hasNext()) {
				Long oid = iterator.next();
				if(oid.compareTo(courseOffer.getCourseOid().getObjectid()) == 0){
					iterator.remove();
				}
				
			}
			List<CourseT> courseList = null;
			if(excludeLists != null && !excludeLists.isEmpty()){
				courseList = courseService.findAllWithExcludesCourse(excludeLists);
			}else{
				courseList = courseService.findAll();
			}
			request.setAttribute("courseList", courseList, WebRequest.SCOPE_REQUEST);
		}
		return "semester.editCourseOffer";
	}
	
	@RequestMapping(value = "/postEditCourseOffer.do", method=RequestMethod.POST)
	public String postEditCourseOffer(WebRequest request){
		String courseOfferOid = request.getParameter("objectid");
		Long semesterOid = null;
		if(StringUtils.isNotBlank(courseOfferOid)) {
			String courseOids = request.getParameter("courseOids");
			CourseOfferT courseOffer = courseService.findById(CourseOfferT.class, Long.valueOf(courseOfferOid));
			if(courseOffer != null){
				semesterOid = courseOffer.getSemesterOid().getObjectid();
				CourseT course = courseService.findById(CourseT.class, Long.valueOf(courseOids));
				
				courseOffer.setCourseOid(course);
				String locationOid= request.getParameter("locationOid");
				if(StringUtils.isNotBlank(locationOid)){
		    		CodeValueT locationCode = codeValueService.findById(CodeValueT.class, Long.valueOf(locationOid));
		    		if(locationCode != null){
		    			courseOffer.setLocationOid(locationCode);
		    		}
		    	}
				courseOfferService.update(courseOffer);
			}
		}
		return "redirect:/semester/getView.do?objectid="+semesterOid;
	}
	
}
