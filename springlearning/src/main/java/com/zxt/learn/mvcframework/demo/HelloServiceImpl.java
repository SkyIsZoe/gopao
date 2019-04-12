package com.zxt.learn.mvcframework.demo;

import com.zxt.learn.mvcframework.annotation.ZxtService;

/**
 * Created by zxt on 2019/4/8.
 */
@ZxtService
public class HelloServiceImpl implements HelloService{

    @Override
    public void sayHello() {
        System.out.println("Hello World");
    }

    @Override
    public String nameAgeHellp(String name, Integer age) {
        String str = "I am "+name+" !I am 2b!" + age;
        System.out.println(str);
        System.out.println("Hello World");
        return str;
    }
}
