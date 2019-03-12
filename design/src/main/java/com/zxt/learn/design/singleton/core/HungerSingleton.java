package com.zxt.learn.design.singleton.core;

/**
 * Created by zxt on 2019/3/12.
 *
 *饿汉式
 */
public class HungerSingleton {

    private static final HungerSingleton INSTANCE = new HungerSingleton();

    private HungerSingleton(){}

    public static HungerSingleton getInstance(){
        return INSTANCE;
    }

}
