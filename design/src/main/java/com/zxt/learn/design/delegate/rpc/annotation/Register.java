package com.zxt.learn.design.delegate.rpc.annotation;

import java.lang.annotation.*;

/**
 * Created by zxt on 2019/3/26.
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Register {

     String value() default "";
}
