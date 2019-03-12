package com.zxt.learn.design.singleton.core;

/**
 * Created by zxt on 2019/3/12.
 */
public class InnerSingleton implements Cloneable{

    static {
        System.out.println("outter class is loader");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private InnerSingleton(){}

    public static InnerSingleton getInstance(){
        return InnerSingletonInner.LZAY;
    }

    private static class InnerSingletonInner{
        static {
            System.out.println("inner class is loader");
        }
        public static final InnerSingleton LZAY = new InnerSingleton();
    }
}
