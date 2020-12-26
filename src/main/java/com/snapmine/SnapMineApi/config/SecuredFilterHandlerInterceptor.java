package com.snapmine.SnapMineApi.config;

import com.snapmine.SnapMineApi.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecuredFilterHandlerInterceptor
 implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod){
			Secured filter = ((HandlerMethod)(handler)).getMethod().getAnnotation(Secured.class);
			if( filter != null){
				System.out.println("DZIALA");
			}
		}
		return true;
	}
}
