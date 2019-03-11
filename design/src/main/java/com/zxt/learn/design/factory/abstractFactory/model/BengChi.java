package com.zxt.learn.design.factory.abstractFactory.model;

import com.zxt.learn.design.factory.abstractFactory.interf.IBinSuX;
import com.zxt.learn.design.factory.abstractFactory.interf.IDiPan;
import com.zxt.learn.design.factory.abstractFactory.interf.IEnige;

/**
 * Created by zxt on 2019/3/11.
 */
public class BengChi extends AbstractCar {

    public BengChi(IEnige enige, IDiPan diPan, IBinSuX binSuX) {
        super(enige, diPan, binSuX);
    }
}
