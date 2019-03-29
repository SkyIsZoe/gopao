package com.zxt.learn.design.delegate.rpc;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by zxt on 2019/3/15.
 */
@Data
public class MethodHandler {

    private Object handler;

    private Method method;

    private Class<?> [] classes;


}







