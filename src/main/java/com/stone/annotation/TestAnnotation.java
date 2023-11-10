package com.stone.annotation;

import java.lang.annotation.*;

/**
 * @author chen
 * @create 2020-02-17 11:50
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotation {

    String content();

    int status() default 0;
}
