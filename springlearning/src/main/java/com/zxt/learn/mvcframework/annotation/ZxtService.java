package com.zxt.learn.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * Created by zxt on 2019/4/1.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZxtService {

    String value()default "";
}
