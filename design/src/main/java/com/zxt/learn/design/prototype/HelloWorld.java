package com.zxt.learn.design.prototype;

/**
 * Created by zxt on 2019/3/15.
 */
public class HelloWorld {

    public static void main(String[] args) {
        IProcess process = ProcessClient.getIntaces().getProcess();
        process.process();
    }
}
