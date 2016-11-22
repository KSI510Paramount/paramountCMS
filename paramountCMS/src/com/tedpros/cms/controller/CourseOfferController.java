package com.tedpros.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.tedpros.cms.entity.CourseOfferT;
import com.tedpros.cms.entity.FacultyT;
import com.tedpros.cms.service.CourseOfferService;

@Controller
@RequestMapping("/courseOffer/*")
public class CourseOfferController extends TopController{
	
	@Autowired
	private CourseOfferService courseOfferService;
	
	@RequestMapping(value = "/getList.do", method=RequestMethod.GET)
	public String getList(WebRequest request){
		FacultyT faculty = (FacultyT) request.getAttribute(LOGIN_USER, WebRequest.SCOPE_SESSION);
		if(faculty != null){
			List<CourseOfferT> courseOfferList = courseOfferService.findByFacultyOid(faculty.getObjectid());
			request.setAttribute("courseOfferList", courseOfferList, WebRequest.SCOPE_REQUEST);			
		}
		return "courseOffer.list";
	}
	
	@RequestMapping(value = "/getEdit.do", method=RequestMethod.GET)
	public String getEdit(WebRequest request){
		String courseOfferOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(courseOfferOid)){
			CourseOfferT courseOffer = courseOfferService.findById(CourseOfferT.class, Long.valueOf(courseOfferOid));
			if(courseOffer != null){
				request.setAttribute("courseOffer", courseOffer, WebRequest.SCOPE_REQUEST);
			}
		}
		return "courseOffer.edit";
	}
	
	@RequestMapping(value = "/postEdit.do", method=RequestMethod.POST)
	public String postEdit(WebRequest request, HttpServletRequest httpRequest){
		try {
			String courseOfferOid= request.getParameter("objectid");
			if(StringUtils.isNotBlank(courseOfferOid)){
				CourseOfferT courseOffer = courseOfferService.findById(CourseOfferT.class, Long.valueOf(courseOfferOid));
				    courseOfferService.update(courseOffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/courseOffer/getList.do";
	}
	
	
	@RequestMapping(value = "/getDelete.do", method=RequestMethod.GET)
	public String getDelete(WebRequest request){
		String courseOfferOid= request.getParameter("objectid");
		if(StringUtils.isNotBlank(courseOfferOid)){
			CourseOfferT courseOffer = courseOfferService.findById(CourseOfferT.class, Long.valueOf(courseOfferOid));
			if(courseOffer != null){
				//courseOfferOfferService.delete(courseOffer);
			}
		}
		return "redirect:/courseOffer/getList.do";
	}
}
