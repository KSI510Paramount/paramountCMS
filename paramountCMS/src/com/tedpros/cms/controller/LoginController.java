package com.tedpros.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/login/*")
public class LoginController extends TopController{

	@RequestMapping(value = "/postLogin.do")
	public String postLogin(WebRequest request){
		System.out.println(getUsername());
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
