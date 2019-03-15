package com.zxt.learn.design.delegate.rpc;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by zxt on 2019/3/15.
 * 假设 我这边实现的是一个 简单RPC 通讯的服务端代码
 *
 * channelRead 是一个委派
 * 通过 ClassHandlerMapping 进行找到对应的handlerMapping
 * 然后 反射调用方法
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        //这个方法 就是典型的委派模式
        HandlerMapping handlerMapping = ClassHandlerMapping.get(this.getClass());//这边只是为了没有编译错误

        //反射调用handler Mapping 方法


    }
}
