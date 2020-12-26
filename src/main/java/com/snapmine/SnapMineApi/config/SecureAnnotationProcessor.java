//package com.snapmine.SnapMineApi.config;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ReflectionUtils;
//
//@Component
//public class SecureAnnotationProcessor implements BeanPostProcessor {
//
//	private ConfigurableListableBeanFactory configurableBeanFactory;
//
//	@Autowired
//	public SecureAnnotationProcessor(ConfigurableListableBeanFactory beanFactory){
//		this.configurableBeanFactory = beanFactory;
//	}
//
//	@Override
//	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//		this.scanSecuredAnnotation(bean,beanName);
//		return bean;
//	}
//
//	@Override
//	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		return bean;
//	}
//
//	protected void scanSecuredAnnotation(Object bean, String beanName){
//		this.configureMethodInjection(bean);
//	}
//
//	private void configureMethodInjection(Object bean){
//		Class<?> managedBeanClass = bean.getClass();
//		ReflectionUtils.MethodCallback methodCallback =
//				new ReactiveMethodCallback(this.configurableBeanFactory, bean);
//		ReflectionUtils.doWithMethods(managedBeanClass, methodCallback);
//	}
//}
//
