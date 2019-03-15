package com.zxt.learn.design.delegate.rpc;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by zxt on 2019/3/15.
 */
public class HandlerMapping {

    private Object handler;

    private Map<String,Method> map;

    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }

    public Map<String, Method> getMap() {
        return map;
    }

    public void setMap(Map<String, Method> map) {
        this.map = map;
    }
}
