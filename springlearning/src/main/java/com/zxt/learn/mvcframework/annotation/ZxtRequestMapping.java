package com.zxt.learn.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * Created by zxt on 2019/4/1.
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZxtRequestMapping {
    String value()default "";
}
