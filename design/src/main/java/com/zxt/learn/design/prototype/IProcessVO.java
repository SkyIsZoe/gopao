package com.zxt.learn.design.prototype;

/**
 * Created by zxt on 2019/3/15.
 */
public class IProcessVO implements IProcess,Cloneable{


    @Override
    public void process() {
        System.out.println("Hello world!");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
