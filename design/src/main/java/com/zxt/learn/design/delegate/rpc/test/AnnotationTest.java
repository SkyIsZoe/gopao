package com.zxt.learn.design.delegate.rpc.test;

import com.zxt.learn.design.delegate.rpc.MethodHandler;
import com.zxt.learn.design.delegate.rpc.annotation.Register;
import com.zxt.learn.design.delegate.rpc.utils.HandlerUtils;
import com.zxt.learn.design.delegate.rpc.utils.MappingUtils;

import java.lang.reflect.Method;

/**
 * Created by zxt on 2019/3/26.
 */
public class AnnotationTest {


    private AnnotationTest(){
        init();
    }

    private void init(){
        Class clazz = this.getClass();
        Method[] methods =clazz.getMethods();
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
                methodHandler.setHandler(this);
                methodHandler.setMethod(method);
                MappingUtils.putMethodHandler(sb.toString(),methodHandler);
            }
        }
    }



    @Register
    public void SayHello(String name,Integer age){
        System.out.println("Hello World!");
    }

    public static void create(){
        AnnotationTest test = new AnnotationTest();
        HandlerUtils.putObj(test.getClass().getName(),test);
    }

    public static void main(String[] args) {
        create();
        MappingUtils.invodeAll();
    }
}
