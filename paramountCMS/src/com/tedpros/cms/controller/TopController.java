package com.tedpros.cms.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.WebRequest;

import com.tedpros.cms.entity.DomainEntity;
import com.tedpros.cms.util.Constants;

public abstract class TopController implements Constants{

	public WebRequestDataBinder getDataBinder(WebRequest request, DomainEntity domainEntity){
		WebRequestDataBinder binder = new WebRequestDataBinder(domainEntity);
		binder.bind(request);
		return binder;
	}
	
	public String getUsername(){
		String username = StringUtils.EMPTY;
		SecurityContext context = SecurityContextHolder.getContext();
		if(context != null){
			Authentication au = context.getAuthentication();
			if(au != null){
				Object principle = au.getPrincipal();
				if(principle instanceof String){
					username = (String) principle;
				}else if(principle instanceof User){
					User user = (User) principle;
					username = user.getUsername();
				}
			}
		}
		return username;
	}
}
