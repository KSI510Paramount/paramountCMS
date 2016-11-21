package com.tedpros.cms.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tedpros.cms.model.LoginRequest;
import com.tedpros.cms.model.LoginResponse;

@Service
public class UserLoginService {
	
	public LoginResponse login(LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
		LoginResponse loginResponse = new LoginResponse();
		String username = loginRequest.getUsername();
		loginResponse.setUsername(username);
		
		Map<String,String> userData = retrieveUserDataFromDB(username);
		if(!userData.isEmpty()){
			String password = userData.get("password");
			String userType = userData.get("userType");
			String lastLoginTime = userData.get("lastLoginTime");
			
			String lastLoginMessage = lastLoginTime == null? "":("Your last login time is " + lastLoginTime);
			
			if(password.equals(loginRequest.getPassword())){
				loginResponse.setUserType(userType);
				loginResponse.setLoginMessage("Welcome "+username+". " + lastLoginMessage);
				httpServletResponse.setStatus(HttpStatus.OK.value());
				return loginResponse;
			}
		}
		
		httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
		loginResponse.setLoginMessage("user:"+username+" or password not found");
		return loginResponse;
	}

	private Map<String,String> retrieveUserDataFromDB(String username) {
		//TODO call DAO retrieve user data based on username, then use return to build hashmap for user data
		//if no user found, return null
		return null;
	}
}
