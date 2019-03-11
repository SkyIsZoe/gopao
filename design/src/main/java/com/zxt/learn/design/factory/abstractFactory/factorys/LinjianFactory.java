package com.zxt.learn.design.factory.abstractFactory.factorys;

import com.zxt.learn.design.factory.abstractFactory.interf.IBinSuX;
import com.zxt.learn.design.factory.abstractFactory.interf.IDiPan;
import com.zxt.learn.design.factory.abstractFactory.interf.IEnige;
import com.zxt.learn.design.factory.abstractFactory.model.BinSux;
import com.zxt.learn.design.factory.abstractFactory.model.DiPan;
import com.zxt.learn.design.factory.abstractFactory.model.Egine;

/**
 * Created by zxt on 2019/3/11.
 */
public class LinjianFactory implements ILinjianFactory{

    @Override
    public IEnige getEnige() {
        return new Egine();
    }

    @Override
    public IBinSuX getBinSuX() {
        return new BinSux();
    }

    @Override
    public IDiPan getDiPan() {
        return new DiPan();
    }
}
