package com.tedpros.cms.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tedpros.cms.model.LoginRequest;
import com.tedpros.cms.model.LoginResponse;
import com.tedpros.cms.service.UserLoginService;

@RestController
public class UserLoginController extends TopController{
	
	@Inject
	private UserLoginService userLoginService;
	
	@RequestMapping(value = "/cms/login",method=RequestMethod.POST,produces={MediaType.APPLICATION_JSON_VALUE})
	public LoginResponse cmsLogin(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse){
		return userLoginService.login(loginRequest, httpServletResponse);
	}

	
}
