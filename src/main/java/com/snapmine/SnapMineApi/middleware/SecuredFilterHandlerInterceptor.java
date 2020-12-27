package com.snapmine.SnapMineApi.middleware;

import com.snapmine.SnapMineApi.annotation.Secured;
import com.snapmine.SnapMineApi.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecuredFilterHandlerInterceptor
 implements HandlerInterceptor {

	final SecurityService securityService;

	public SecuredFilterHandlerInterceptor(SecurityService securityService) {
		this.securityService = securityService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod){
			Secured filter = ((HandlerMethod)(handler)).getMethod().getAnnotation(Secured.class);
			if( filter != null){
//				int id = Integer.parseInt(request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/') + 1) );
//				System.out.println(id);
			}
		}
		return true;
	}
}
