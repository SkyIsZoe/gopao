package com.zxt.learn.design.delegate.rpc.handler;

import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by zxt on 2019/2/22.
 */
public interface DecodersFactory {

    List<ByteToMessageDecoder> getDecoders();
}
