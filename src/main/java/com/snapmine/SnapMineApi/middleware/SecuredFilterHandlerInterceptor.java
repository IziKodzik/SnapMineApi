package com.snapmine.SnapMineApi.middleware;

import com.google.gson.Gson;
import com.snapmine.SnapMineApi.annotation.Secured;
import com.snapmine.SnapMineApi.model.dtos.response.AuthResponse;
import com.snapmine.SnapMineApi.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;

@Component
public class SecuredFilterHandlerInterceptor
 implements HandlerInterceptor {

	final SecurityService securityService;
	Gson gson = new Gson();

	@Autowired
	public SecuredFilterHandlerInterceptor(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod){
			Secured filter = ((HandlerMethod)(handler)).getMethod().getAnnotation(Secured.class);
			if( filter != null){
				String hashedToken = request.getHeader("token");
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				if(hashedToken==null) {
					response.setStatus(400);
					response.getWriter().write("{ \"message\" : \"Api requires a token.\"}");
					return false;
				}

			}
		}
		return true;
	}
}
