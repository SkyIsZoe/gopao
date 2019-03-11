package com.zxt.learn.design.factory.factory;

import com.zxt.learn.design.factory.po.BMW;
import com.zxt.learn.design.factory.po.Car;

/**
 * Created by zxt on 2019/3/11.
 */
public class BMWFactory implements ICar {

    @Override
    public Car getCar() {
        return new BMW();
    }

}
