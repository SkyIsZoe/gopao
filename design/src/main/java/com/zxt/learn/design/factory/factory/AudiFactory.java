package com.zxt.learn.design.factory.factory;

import com.zxt.learn.design.factory.po.Audi;
import com.zxt.learn.design.factory.po.Car;

/**
 * Created by zxt on 2019/3/11.
 */
public class AudiFactory implements ICar{
    @Override
    public Car getCar() {
       return new Audi();
    }
}
