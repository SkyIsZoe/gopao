package com.zxt.learn.design.delegate.rpc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxt on 2019/3/15.
 */
public class ClassHandlerMapping {

    private static Map<Class,HandlerMapping> map;

    static {
        map = new HashMap<>();
    }

    public static HandlerMapping get(Class<?> clazz){
        return map.get(clazz);
    }

    public static Map<Class,HandlerMapping> put(Class<?> clazz,HandlerMapping handlerMapping){
        map.put(clazz,handlerMapping);
        return map;
    }
}
