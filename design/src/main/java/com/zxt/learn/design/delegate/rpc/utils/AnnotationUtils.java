package com.zxt.learn.design.delegate.rpc.utils;

import com.zxt.learn.design.delegate.rpc.MethodHandler;
import com.zxt.learn.design.delegate.rpc.annotation.Register;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by zxt on 2019/3/29.
 */
public class AnnotationUtils {

    public static void initRegister(Class<?> clazz)throws Exception{
        Method[] methods =clazz.getDeclaredMethods();
        Constructor constructor = clazz.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        if(constructor==null){
            throw new Exception("this is no default constructor");
        }
        Object obj = constructor.newInstance();
        for(Method method:methods){
            if(method.isAnnotationPresent(Register.class)){
                String methodNmae = method.getName();
                String className = clazz.getSimpleName();
                Class[] classes = method.getParameterTypes();
                StringBuilder sb = new StringBuilder();
                sb.append(className).append(".").append(methodNmae).append(":");
                for(Class cla:classes){
                    sb.append(cla.getSimpleName()).append(",");
                }
                if(sb.length()>0){
                    sb.deleteCharAt(sb.length()-1);
                }
                MethodHandler methodHandler = new MethodHandler();
                methodHandler.setClasses(classes);
                methodHandler.setHandler(obj);
                methodHandler.setMethod(method);
                MappingUtils.putMethodHandler(sb.toString(),methodHandler);
            }
        }
    }
}
