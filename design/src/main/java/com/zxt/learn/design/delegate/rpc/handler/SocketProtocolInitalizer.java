package com.zxt.learn.design.delegate.rpc.handler;




import com.zxt.learn.design.delegate.rpc.abstractProcess.AbstractProcess;
import com.zxt.learn.design.delegate.rpc.confing.DeviceConfig;
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


    private List<AbstractProcess> deviceProcessList;

    private DeviceConfig deviceConfig;

    private DecodersFactory factory;

    public SocketProtocolInitalizer(List<AbstractProcess> deviceProcessList, DeviceConfig deviceConfig, DecodersFactory factory){
        this.deviceConfig=deviceConfig;
        this.deviceProcessList = deviceProcessList;
        this.factory = factory;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        LOG.info("init socket");
        try {
            List<ByteToMessageDecoder> decoders = factory.getDecoders();
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
            if (null != decoders ) {
                for (ByteToMessageDecoder decoder : decoders) {
                    pipeline.addLast(decoder);
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
