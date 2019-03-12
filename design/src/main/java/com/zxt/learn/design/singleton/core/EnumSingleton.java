package com.zxt.learn.design.singleton.core;

/**
 * Created by zxt on 2019/3/12.
 * 避免发射破坏单例
 * 避免序列化破坏
 *
 */

public enum  EnumSingleton {
    INSTANCE;
    public EnumSingleton getInstance(){
        return INSTANCE;
    }
}
