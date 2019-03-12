package com.zxt.learn.design.singleton;

import com.zxt.learn.design.singleton.core.InnerSingleton;

/**
 * Created by zxt on 2019/3/12.
 */
public class Test {
    public static void main(String[] args) {
        InnerSingleton innerSingleton1 =InnerSingleton.getInstance();
        //outter class is loader
        //inner class is loader


        //先加载外部类 在加载内部类  当内部类被加载的时候 常量被初始化 这个时候 不会有线程安全问题 这个是由JDK 线程完成的
    }
}
