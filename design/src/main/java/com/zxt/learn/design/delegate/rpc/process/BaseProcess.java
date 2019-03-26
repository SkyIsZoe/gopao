package com.zxt.learn.design.delegate.rpc.process;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

@ChannelHandler.Sharable
public abstract class BaseProcess implements Process {
	protected ChannelHandlerContext ctx = null;

	protected Map<String,Object> msg;


	//是否继续执行
	protected boolean isContinue = true;

	protected boolean isUpdateSession = false;

	public void init(Map<String, Object> msg) throws Exception {
		this.msg = msg;
		this.ctx = (ChannelHandlerContext) msg.get(MessageKeyword.channelHandlerContext);
		this.isUpdateSession = (boolean) msg.get(MessageKeyword.isUpdateSession);
	}

	public void reply() throws Exception {

	}

	public boolean getIsContinue() {
		return isContinue;
	}
}
