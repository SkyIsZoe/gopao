package com.zxt.learn.design.factory.abstractFactory.model;

import com.zxt.learn.design.factory.abstractFactory.interf.IEnige;

/**
 * Created by zxt on 2019/3/11.
 */
public class Egine implements IEnige{

    private String name;


    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public void fadong() {
        System.out.println("发动");
    }
}
