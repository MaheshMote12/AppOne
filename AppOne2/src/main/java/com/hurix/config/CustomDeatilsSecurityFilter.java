package com.hurix.config;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

public class CustomDeatilsSecurityFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		 SecurityContext sec = SecurityContextHolder.getContext();
	      AbstractAuthenticationToken auth = (AbstractAuthenticationToken)sec.getAuthentication();
	      
 	      
	      HashMap<String, Object> info = new HashMap<String, Object>();
	      info.put("companyId", 42);
	      auth.setDetails(info);
		
	}

}