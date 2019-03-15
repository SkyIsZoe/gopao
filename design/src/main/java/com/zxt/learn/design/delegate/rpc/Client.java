package com.zxt.learn.design.delegate.rpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLEngine;

/**
 * Created by zxt on 2019/3/15.
 */
@Component
public class Client {

    @Autowired
    private SslContext sslCtx;



    @Value("${socket.server.address}")
    private String host;

    @Value("${socket.server.port}")
    private Integer port;

    public void connect(ChannelInboundHandlerAdapter handler,boolean sslFlag) throws Exception {

        new Thread(() -> {
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            try {
                Bootstrap b = new Bootstrap();
                b.group(workerGroup);
                b.channel(NioSocketChannel.class);
                b.option(ChannelOption.SO_KEEPALIVE, true);
                b.handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        if (sslFlag) {
                            SSLEngine engine = sslCtx.newEngine(ch.alloc());
                            engine.setUseClientMode(true);
                            ch.pipeline().addFirst("ssl", new SslHandler(engine, false));
                        }
                        ch.pipeline().addLast(handler);
                    }
                });

                // Start the client.
                ChannelFuture f = null;
                try {
                    f = b.connect(host, port).sync();
                    f.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Wait until the connection is closed.

            } finally {
                workerGroup.shutdownGracefully();
            }
        }).start();
    }
}
