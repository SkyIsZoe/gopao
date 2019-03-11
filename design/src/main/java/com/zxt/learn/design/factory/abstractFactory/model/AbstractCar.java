package com.zxt.learn.design.factory.abstractFactory.model;

import com.zxt.learn.design.factory.abstractFactory.interf.IBinSuX;
import com.zxt.learn.design.factory.abstractFactory.interf.IDiPan;
import com.zxt.learn.design.factory.abstractFactory.interf.IEnige;
import com.zxt.learn.design.factory.po.Car;
import lombok.Data;

/**
 * Created by zxt on 2019/3/11.
 */
@Data
public class AbstractCar extends Car {

    private IEnige enige;

    private IDiPan diPan;

    private IBinSuX binSuX;


    public AbstractCar(IEnige enige, IDiPan diPan, IBinSuX binSuX) {
        super();
        this.enige = enige;
        this.diPan = diPan;
        this.binSuX = binSuX;
    }
}
