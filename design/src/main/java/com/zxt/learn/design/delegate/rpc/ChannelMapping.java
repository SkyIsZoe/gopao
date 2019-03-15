package com.zxt.learn.design.delegate.rpc;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxt on 2019/3/15.
 */
public class ChannelMapping {

    private static Map<String,ChannelHandlerContext> map ;

    static {
        map = new HashMap<>();
    }

    public static Map<String,ChannelHandlerContext> putCtx(String serverName,ChannelHandlerContext ctx){
        map.put(serverName,ctx);
        return map;
    }
}
