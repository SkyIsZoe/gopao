package com.zxt.learn.design.delegate.rpc.confing;

;
import com.zxt.learn.design.delegate.rpc.abstractProcess.AbstractProcess;
import com.zxt.learn.design.delegate.rpc.handler.DecodersFactory;
import com.zxt.learn.design.delegate.rpc.handler.SocketProtocolInitalizer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zxt on 2019/1/28.
 */
public class StartSocket {

    private static Logger log = LoggerFactory.getLogger(StartSocket.class);

    private List<AbstractProcess> deviceProcessList;

    private List<DeviceConfig> deviceConfigList;

    private DecodersFactory factory;

    public StartSocket(List<AbstractProcess> deviceProcessList, List<DeviceConfig> deviceConfigList, DecodersFactory factory){
        this.deviceConfigList = deviceConfigList;
        this.deviceProcessList = deviceProcessList;
        this.factory = factory;
    }

    public void start() throws Exception{
        if(deviceConfigList == null){
            return;
        }
        for (DeviceConfig deviceConfig:deviceConfigList){
            run(deviceConfig);
        }
    }

    private  void run(DeviceConfig deviceConfig) throws Exception{
        ServerBootstrap bootStrap = new ServerBootstrap();
        bootStrap.group(bossGroup(deviceConfig), workerGroup(deviceConfig)).channel(NioServerSocketChannel.class)
                .childHandler(new SocketProtocolInitalizer(deviceProcessList,deviceConfig,factory));
        Map<ChannelOption<?>, Object> tcpChannelOptions = tcpChannelOptions(deviceConfig);
        Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
        for (ChannelOption option : keySet) {
            bootStrap.option(option, tcpChannelOptions.get(option));
        }
        ChannelFuture serverChannelFuture = bootStrap.bind(deviceConfig.getTcpPort()).sync();
        // common.getNETTY_SERVER_BS() .put(deviceKey, bootStrap);
        StartCommon.getNetty_bootstrap().put(deviceConfig.getServerName(), bootStrap);
        StartCommon.getNetty_channelFuture().put(deviceConfig.getServerName(), serverChannelFuture);
        log.info("start " + deviceConfig.getServerName() + " bootstrap,port:" + deviceConfig.getTcpPort());
    }


    private NioEventLoopGroup bossGroup(DeviceConfig deviceConfig) {
        return new NioEventLoopGroup(deviceConfig.getBossCount());
    }

    private NioEventLoopGroup workerGroup(DeviceConfig deviceConfig) {
        return new NioEventLoopGroup(deviceConfig.getWorkerCount());
    }

    private   Map<ChannelOption<?>, Object> tcpChannelOptions(DeviceConfig deviceConfig) {
        Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();
        options.put(ChannelOption.SO_KEEPALIVE, deviceConfig.isKeepAlive());
        options.put(ChannelOption.SO_BACKLOG, deviceConfig.getBacklog());
        return options;
    }
}
