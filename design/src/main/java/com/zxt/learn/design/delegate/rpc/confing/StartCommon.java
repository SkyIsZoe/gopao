package com.zxt.learn.design.delegate.rpc.confing;



import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.HashMap;
import java.util.Map;

public class StartCommon {



	// netty bootstrap maps
	private static Map<String, ServerBootstrap> netty_bootstrap = new HashMap<>();

	// netty channelFuture maps
	private static Map<String, ChannelFuture> netty_channelFuture = new HashMap<>();

	//channel缓存
	private static Map<String, Channel> netty_channel = new HashMap<>();

	private static Map<String,String> channel_vin = new HashMap<>();




	public static Map<String, ServerBootstrap> getNetty_bootstrap() {
		return netty_bootstrap;
	}

	public static void setNetty_bootstrap(Map<String, ServerBootstrap> netty_bootstrap) {
		StartCommon.netty_bootstrap = netty_bootstrap;
	}

	public static Map<String, ChannelFuture> getNetty_channelFuture() {
		return netty_channelFuture;
	}

	public static void setNetty_channelFuture(Map<String, ChannelFuture> netty_channelFuture) {
		StartCommon.netty_channelFuture = netty_channelFuture;
	}

	public static Map<String, Channel> getNetty_channel() {
		return netty_channel;
	}

	public static void setNetty_channel(Map<String, Channel> netty_channel) {
		StartCommon.netty_channel = netty_channel;
	}

	public static String getChannel_vin(String channelId) {
		return channel_vin.get(channelId);
	}

	public static void removeChannel_vin(String channelId){
		channel_vin.remove(channelId);
	}

	public static void putChannel_vin(String channelId,String vin) {
		StartCommon.channel_vin.put(channelId,vin);
	}

}
