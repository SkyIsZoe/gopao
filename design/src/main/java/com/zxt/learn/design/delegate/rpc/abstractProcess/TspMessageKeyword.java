package com.zxt.learn.design.delegate.rpc.abstractProcess;


import com.zxt.learn.design.delegate.rpc.process.MessageKeyword;

public interface TspMessageKeyword extends MessageKeyword {
	public static final String byteMsg = "byteMsg";
	public static final String hexMsg = "hexMsg";
	public static final String parsedData = "parsedData";
	public static final String byteBuffer = "byteBuffer";
	public static final String expirySeconds = "expirySeconds";
}
