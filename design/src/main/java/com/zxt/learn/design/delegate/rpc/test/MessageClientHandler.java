package com.zxt.learn.design.delegate.rpc.test;

import com.zxt.learn.design.delegate.rpc.learnReflect.HelloWorld;
import com.zxt.learn.design.delegate.rpc.messge.MessageRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by zxt on 2019/2/15.
 */
public class MessageClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setClassName(AnnotationTest.class.getSimpleName());
        messageRequest.setMethodName("SayHello");
        messageRequest.setTypeParameters(new Class[]{String.class,Integer.class});
        messageRequest.setParameters(new Object[]{null,null});
        ctx.writeAndFlush(messageRequest);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
