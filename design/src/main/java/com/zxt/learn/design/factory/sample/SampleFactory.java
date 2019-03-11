package com.zxt.learn.design.factory.sample;

import com.zxt.learn.design.factory.po.Audi;
import com.zxt.learn.design.factory.po.BMW;

/**
 * Created by zxt on 2019/3/11.
 */
public class SampleFactory {

    public static   <T>T create(Class<T> clazz){
        if(clazz!=null){
            switch (clazz.getSimpleName()){
            case "BMW" : return (T) getBMW();
            case "Audi": return  (T) getAudi();
            }
        }
        return null;
    }

    private static BMW getBMW(){
        return new BMW();
    }

    private static Audi getAudi(){
        return new Audi();
    }
}
