package com.zxt.learn.design.factory.abstractFactory.factorys;

import com.zxt.learn.design.factory.abstractFactory.interf.IBinSuX;
import com.zxt.learn.design.factory.abstractFactory.interf.IDiPan;
import com.zxt.learn.design.factory.abstractFactory.interf.IEnige;
import com.zxt.learn.design.factory.abstractFactory.model.AbstractCar;
import com.zxt.learn.design.factory.abstractFactory.model.BengChi;
import com.zxt.learn.design.factory.abstractFactory.model.Egine;
import com.zxt.learn.design.factory.po.Car;

/**
 * Created by zxt on 2019/3/11.
 */
public class CarFactory {

    public static final CarFactory FACTORY = new CarFactory();

    private ILinjianFactory linjianFactory;

    private CarFactory(){
        linjianFactory = new LinjianFactory();
    }

    public AbstractCar cerateCar(String egineName,String binSuxName,String dipanName){
        IEnige egine = linjianFactory.getEnige();
        egine.setName(egineName);
        IDiPan diPan = linjianFactory.getDiPan();
        diPan.setName(dipanName);
        IBinSuX binSuX = linjianFactory.getBinSuX();
        binSuX.setName(binSuxName);
        BengChi bengChi = new BengChi(egine,diPan,binSuX);
        return bengChi;
    }

    public static void main(String[] args) {
        System.out.println(CarFactory.FACTORY.cerateCar("V12","超级变速箱","超级底盘"));;
    }
}
