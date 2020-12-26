//package com.snapmine.SnapMineApi.config;
//
//import com.snapmine.SnapMineApi.annotation.Secured;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.util.ReflectionUtils;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//
//public class ReactiveMethodCallback implements ReflectionUtils.MethodCallback {
//
//	private ConfigurableListableBeanFactory beanFactory;
//	private Object bean;
//
//	public ReactiveMethodCallback(ConfigurableListableBeanFactory beanFactory, Object bean) {
//		this.beanFactory = beanFactory;
//		this.bean = bean;
//	}
//
//	@Override
//	public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
//		if (method.isAnnotationPresent(Secured.class)){
//
//			System.out.println("essa");
//		}
//
//	}
//}
