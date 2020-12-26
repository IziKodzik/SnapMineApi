package com.snapmine.SnapMineApi.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Secured {

	Integer ttl = null;
}
