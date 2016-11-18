package com.tedpros.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.tedpros.cms.entity.FacultyT;
import com.tedpros.cms.service.FacultyService;

@Controller
@RequestMapping("/login/*")
public class LoginController extends TopController{

	@Autowired
	private FacultyService facultyService;
	
	@RequestMapping(value = "/postLogin.do")
	public String postLogin(WebRequest request){
		FacultyT faculty = facultyService.findByUsername(getUsername());
		request.setAttribute(LOGIN_USER, faculty, WebRequest.SCOPE_SESSION);
		return getHome(request);
	}
	
	@RequestMapping(value = "/getHome.do")
	public String getHome(WebRequest request){
		return "login.home";
	}
	
	@RequestMapping("/getAccessDenied.do")
	public String getAccessDenied(WebRequest request){
		System.out.println(getUsername());
		return "login.home";
	}
}
