package com.zxt.learn.design.delegate.rpc.handler;


import com.zxt.learn.design.delegate.rpc.confing.StartCommon;
import com.zxt.learn.design.delegate.rpc.messge.MessageRequest;
import com.zxt.learn.design.delegate.rpc.process.MessageKeyword;
import com.zxt.learn.design.delegate.rpc.process.Process;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Sharable
public class DeviceHandler extends ChannelInboundHandlerAdapter {
	// ChannelGroup group = new
	// DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	private static final Logger LOG = LoggerFactory.getLogger(DeviceHandler.class);


	private List<Process> deviceProcessList;

	public DeviceHandler(List<Process> deviceProcessList){
		this.deviceProcessList = deviceProcessList;
	}


	private boolean isUpdateSession = false;

	/*
	 * @Autowired
	 * 
	 * @Qualifier("startCommon") private StartCommon startCommon;
	 */

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		try {
			Channel channel = ctx.channel();
			StartCommon.getNetty_channel().put(channel.id().asShortText(), channel);
			isUpdateSession = true;
		} catch (Exception e) {
			LOG.error("add channel session error!", e);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		LOG.debug("*********** channelRead started ***********");
		try {
			for (Process process:deviceProcessList){
				Map<String,Object> map =new HashMap<>();
				map.put("request",msg);
				process.init(map);
				process.parse();
			}
			LOG.debug("*********** channelRead Ends ***********");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
			ReferenceCountUtil.release(msg); // (2)
		}

	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		/*try {
			Channel channel = ctx.channel();
			Channel keepChannel = StartCommon.getNetty_channel().get(channel.id().asShortText());
			if (null != keepChannel) {
				StartCommon.getNetty_channel().remove(channel.id().asShortText());
			}
		} catch (Exception e) {
			LOG.error("remove channel session error!", e);
		}*/
		ctx.flush();
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		LOG.info("*********** channelInactive ***********");
		super.channelInactive(ctx);
	}
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
	}
	
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		super.channelWritabilityChanged(ctx);
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		LOG.debug("remove channel:"+ctx.channel());
		LOG.info("remove channel :"+ctx.channel());
		try {
			remove(ctx);
		} catch (Exception e) {
			LOG.error("remove channel session error!", e);
		}
		super.handlerRemoved(ctx);
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		super.userEventTriggered(ctx, evt);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		try {
			LOG.info("exception channel :"+ctx.channel(),cause);
			remove(ctx);
		} catch (Exception e) {
			LOG.error("remove channel session error!", e);
		}
		super.exceptionCaught(ctx, cause);
		LOG.error("exception channel :"+ctx.channel(),cause);
		ctx.close();
	}


	private void remove(ChannelHandlerContext ctx){
		Channel channel = ctx.channel();
		Channel keepChannel = StartCommon.getNetty_channel().get(channel.id().asShortText());
		if (null != keepChannel) {
			StartCommon.getNetty_channel().remove(channel.id().asShortText());
			StartCommon.removeChannel_vin(channel.id().asShortText());

		}
	}

}
