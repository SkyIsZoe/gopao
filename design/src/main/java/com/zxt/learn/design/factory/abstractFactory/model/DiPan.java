package com.zxt.learn.design.factory.abstractFactory.model;

import com.zxt.learn.design.factory.abstractFactory.interf.IDiPan;

/**
 * Created by zxt on 2019/3/11.
 */
public class DiPan implements IDiPan{

    private String mame;

    @Override
    public void setName(String name) {
        this.mame =name;
    }

    @Override
    public void zhuangzai() {
        System.out.println("装载");
    }
}
