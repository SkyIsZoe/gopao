package com.zxt.learn.design.delegate.rpc.learnReflect;

import com.zxt.learn.design.delegate.rpc.handler.HandlerFactory;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxt on 2019/3/29.
 */
public class HandlerFactoryImpl implements HandlerFactory{
    @Override
    public List<ChannelHandler> getHandlers() {
        List<ChannelHandler> list = new ArrayList<>();
        list.add(new ObjectDecoder(1024*1024, ClassResolvers.weakCachingResolver(this.getClass().getClassLoader())));
        list.add(new ObjectEncoder());
        return list;
    }
}
