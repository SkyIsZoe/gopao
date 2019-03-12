package com.zxt.learn.design.singleton.core;

/**
 * Created by zxt on 2019/3/12.
 * 懒汉式
 */
public class LazySingleton {

    private LazySingleton(){}

    private static LazySingleton lazySingleton;



    public static LazySingleton getLazySingleton(){
        if(lazySingleton!=null){
            return lazySingleton;
        }else {
            synchronized (LazySingleton.class){
                if(lazySingleton==null){
                    lazySingleton= new LazySingleton();
                }
            }
        }
        return lazySingleton;

    }
}
