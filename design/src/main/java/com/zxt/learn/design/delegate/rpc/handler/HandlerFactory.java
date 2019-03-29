package com.zxt.learn.design.delegate.rpc.handler;

import io.netty.channel.ChannelHandler;

import java.util.List;

/**
 * Created by zxt on 2019/3/29.
 */
public interface HandlerFactory {

    List<ChannelHandler> getHandlers();
}
