package com.zxt.learn.design.prototype;

/**
 * Created by zxt on 2019/3/15.
 */
public class ProcessClient {

    private static final ProcessClient CLIENT = new ProcessClient();

    private ProcessClient(){
        process = new IProcessVO();
    }

    private IProcessVO process;

    public IProcess getProcess(){
        try {
            return (IProcessVO)process.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ProcessClient getIntaces(){
        return CLIENT;
    }
}
