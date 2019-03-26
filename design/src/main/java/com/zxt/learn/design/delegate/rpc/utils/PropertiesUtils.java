package com.zxt.learn.design.delegate.rpc.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Properties;


/**
 * Created by zxt on 2019/1/3.
 */
public class PropertiesUtils {

    public static Properties load(String path, Class clazz) throws Exception {
        System.out.println("PropertiesUtils:" + PropertiesUtils.class.getClassLoader());
        InputStream in = null;
        if (StringUtils.isNotEmpty(path) && path.startsWith("/")) {
            in = clazz.getResourceAsStream(path);
            if(null == in) {
                in = clazz.getClassLoader().getResourceAsStream(path);
            }
        } else {
            in = PropertiesUtils.class.getClassLoader().getResourceAsStream(path);
            if(null == in) {
                in = PropertiesUtils.class.getResourceAsStream(path);
            }
        }
        Properties prop = new Properties();
        prop.load(in);
        return prop;
    }


    public static Properties load(String path) throws Exception {
        return load(path, PropertiesUtils.class);
    }


    public static int getInt(String str,int defaultNumber){

        if(str==null||"".equals(str)){
            return defaultNumber;
        }
        try {
            int value = Integer.valueOf(str);
            return value;
        }catch (Exception e){
            return defaultNumber;
        }
    }

    public static boolean getBoolean(String str){

        if(str==null||"".equals(str)){
            return true;
        }
        try {
            if("false".equals(str)){
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            return true;
        }
    }

}
