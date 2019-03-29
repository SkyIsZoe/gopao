package com.zxt.learn.design.delegate.rpc.Server;

import com.zxt.learn.design.delegate.rpc.abstractProcess.ExecuteProcess;
import com.zxt.learn.design.delegate.rpc.confing.DeviceConfig;
import com.zxt.learn.design.delegate.rpc.confing.StartSocket;
import com.zxt.learn.design.delegate.rpc.handler.HandlerFactory;
import com.zxt.learn.design.delegate.rpc.learnReflect.HandlerFactoryImpl;
import com.zxt.learn.design.delegate.rpc.process.Process;
import com.zxt.learn.design.delegate.rpc.test.AnnotationTest;
import com.zxt.learn.design.delegate.rpc.utils.AnnotationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxt on 2019/3/29.
 */
public class ServerStart {

    private static List<DeviceConfig> initConfig(){
        DeviceConfig   deviceConfig  = new DeviceConfig();
        deviceConfig.setServerName("hahaha");
        deviceConfig.setAllIdleTime(180);
        deviceConfig.setWriterIdleTime(180);
        deviceConfig.setReaderIdleTime(180);
        deviceConfig.setTcpPort(6998);
        deviceConfig.setBacklog(3600);
        deviceConfig.setKeepAlive(true);
        deviceConfig.setWriterIdleTime(4);
        deviceConfig.setBossCount(4);
        List<DeviceConfig> list = new ArrayList<>();
        list.add(deviceConfig);
        return list;
    }

    private static List<Process> initProcess(){
        List<Process> list = new ArrayList<>();
        ExecuteProcess process = new ExecuteProcess();
        list.add(process);
        return list;
    }

    public static void main(String[] args) {
        try {
            AnnotationUtils.initRegister(AnnotationTest.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HandlerFactory handlerFactory = new HandlerFactoryImpl();
        List<Process> processlist = initProcess();
        List<DeviceConfig> deviceConfigs = initConfig();
        StartSocket startSocket = new StartSocket(processlist,deviceConfigs,handlerFactory);
        try {
            startSocket.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
