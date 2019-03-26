package com.zxt.learn.design.delegate.rpc.learnReflect;

import com.zxt.learn.design.delegate.rpc.messge.MessageRequest;
import sun.reflect.misc.MethodUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zxt on 2019/3/18.
 */
public class TestRef {

    public static void main(String[] args) {
//        HelloWorld helloWorld = new HelloWorld();
//        Class clazz = helloWorld.getClass();
//        try {
//            Method sayhello = clazz.getMethod("SayHello",String.class,Integer.class);
//            try {
//                sayhello.invoke(helloWorld,null,null);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }

        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setClassName(HelloWorld.class.getName());
        messageRequest.setMethodName("SayHello");
        messageRequest.setTypeParameters(new Class[]{String.class,Integer.class});
        messageRequest.setParameters(new Object[]{null,null});

        try {
            Class  clazz = Class.forName(messageRequest.getClassName());
            Object obj = clazz.getConstructor(null).newInstance();
            Method method = clazz.getMethod(messageRequest.getMethodName(),messageRequest.getTypeParameters());
            MethodUtil.invoke(method,obj,messageRequest.getParameters());

        } catch (NoSuchMethodException e) {
                e.printStackTrace();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
