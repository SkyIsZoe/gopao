package com.zxt.learn.design.delegate.rpc.handler;




import com.zxt.learn.design.delegate.rpc.abstractProcess.AbstractProcess;
import com.zxt.learn.design.delegate.rpc.confing.DeviceConfig;
import com.zxt.learn.design.delegate.rpc.process.Process;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@Sharable
public class SocketProtocolInitalizer extends ChannelInitializer<SocketChannel> {
    private static final Logger LOG = LoggerFactory.getLogger(SocketProtocolInitalizer.class);


    private List<Process> deviceProcessList;

    private DeviceConfig deviceConfig;

    private HandlerFactory factory;

    public SocketProtocolInitalizer(List<Process> deviceProcessList, DeviceConfig deviceConfig, HandlerFactory factory){
        this.deviceConfig=deviceConfig;
        this.deviceProcessList = deviceProcessList;
        this.factory = factory;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        LOG.info("init socket");
        try {
            List<ChannelHandler> handlers = factory.getHandlers();
            ChannelPipeline pipeline = ch.pipeline();
            // ByteBuf delimiter = Unpooled.copiedBuffer("#".getBytes());
            // pipeline.addLast(new DelimiterBasedFrameDecoder(2048,
            // delimiter));
            // pipeline.addLast("decoder", stringEncoder);
            // pipeline.addLast(obdHandler);
            // pipeline.addLast("readTimeoutHandler",new
            // ReadTimeoutHandler(60));
            pipeline.addLast("idleStateHandler",
                    new IdleStateHandler(deviceConfig.getReaderIdleTime(),
                            deviceConfig.getWriterIdleTime(),
                            deviceConfig.getAllIdleTime()));
            if (null != handlers ) {
                for (ChannelHandler handler : handlers) {
                    pipeline.addLast(handler);
                }
            }

            pipeline.addLast("handler", new DeviceHandler(deviceProcessList));
            // pipeline.addLast("encoder", stringEncoder);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {

        }
    }



}
