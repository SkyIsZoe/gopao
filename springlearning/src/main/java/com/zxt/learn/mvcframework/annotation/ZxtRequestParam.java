package com.zxt.learn.mvcframework.annotation;

import com.sun.org.apache.regexp.internal.RE;

import java.lang.annotation.*;

/**
 * Created by zxt on 2019/4/1.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZxtRequestParam {
    String value()default "";
}
