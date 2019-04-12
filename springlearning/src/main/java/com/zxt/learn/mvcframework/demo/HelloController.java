package com.zxt.learn.mvcframework.demo;

import com.zxt.learn.mvcframework.annotation.ZxtAutowired;
import com.zxt.learn.mvcframework.annotation.ZxtController;
import com.zxt.learn.mvcframework.annotation.ZxtRequestMapping;
import com.zxt.learn.mvcframework.annotation.ZxtRequestParam;


/**
 * Created by zxt on 2019/4/8.
 */
@ZxtController
@ZxtRequestMapping("/hello")
public class HelloController {

    @ZxtAutowired
    private HelloService service;

    @ZxtRequestMapping("/sayHello")
    public void Say(){
        service.sayHello();
    }
    @ZxtRequestMapping("/nameAge")
    public String nameAgeHello(@ZxtRequestParam("name") String name, @ZxtRequestParam("age")Integer age){
        return service.nameAgeHellp(name,age);
    }
}
