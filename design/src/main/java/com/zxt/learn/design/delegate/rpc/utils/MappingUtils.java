package com.zxt.learn.design.delegate.rpc.utils;

import com.zxt.learn.design.delegate.rpc.MethodHandler;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxt on 2019/3/26.
 */
public class MappingUtils {

    private static Map<String,MethodHandler> map ;

    static {
        map = new HashMap<>();
    }

    public static MethodHandler getMethodHandler(String str){
        return map.get(str);
    }

    public static void  putMethodHandler(String str ,MethodHandler methodHandler){
        map.put(str,methodHandler);
    }

    public static void invodeAll(){
        map.forEach((k,v)->{
            System.out.println(k);
            try {
                v.getMethod().invoke(v.getHandler(),new Object[]{null,null});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }
}
