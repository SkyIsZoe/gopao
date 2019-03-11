package com.zxt.learn.design.factory.abstractFactory.model;

import com.zxt.learn.design.factory.abstractFactory.interf.IBinSuX;

/**
 * Created by zxt on 2019/3/11.
 */
public class BinSux implements IBinSuX{

    private String mame;

    @Override
    public void setName(String name) {
        this.mame =name;
    }

    @Override
    public void biansu() {
        System.out.println("biansu");
    }
}
