package com.snapmine.SnapMineApi.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Secured {

	int idPos() default -1;
	String[] roles() default {"no"};

}
