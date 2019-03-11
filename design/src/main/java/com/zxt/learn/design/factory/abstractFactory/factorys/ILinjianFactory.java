package com.zxt.learn.design.factory.abstractFactory.factorys;

import com.zxt.learn.design.factory.abstractFactory.interf.IBinSuX;
import com.zxt.learn.design.factory.abstractFactory.interf.IDiPan;
import com.zxt.learn.design.factory.abstractFactory.interf.IEnige;

/**
 * Created by zxt on 2019/3/11.
 */
public interface ILinjianFactory {

    IEnige getEnige();

    IBinSuX getBinSuX();

    IDiPan getDiPan();
}
