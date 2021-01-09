package com.snapmine.SnapMineApi.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Secured {

	String[] roles() default {"no"};

}
