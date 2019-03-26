package com.zxt.learn.design.delegate.rpc.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxt on 2019/3/26.
 */
public class HandlerUtils {
    private static Map<String,Object> map;

    static {
        map = new HashMap<>();
    }

    public static Object getObj (String k){
        return map.get(k);
    }

    public static void putObj(String key,Object o){
        map.put(key,o);
    }
}
