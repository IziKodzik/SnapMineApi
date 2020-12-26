package com.snapmine.SnapMineApi.config;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomizedConfig
	implements WebMvcConfigurer {

	SecuredFilterHandlerInterceptor reactiveFilterHandlerInterceptor;

	@Autowired
	public CustomizedConfig(SecuredFilterHandlerInterceptor s){
		this.reactiveFilterHandlerInterceptor = s;
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(reactiveFilterHandlerInterceptor);
	}
}
