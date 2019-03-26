package com.zxt.learn.design.delegate.rpc.confing;




import com.zxt.learn.design.delegate.rpc.utils.PropertiesUtils;

import java.util.Properties;

public class DeviceConfig {

	private String serverName;

	private int bossCount;

	private int workerCount;

	private int tcpPort;

	private boolean keepAlive;

	private int backlog;

	private int readerIdleTime;

	private int writerIdleTime;

	private int allIdleTime;


	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public int getBossCount() {
		return bossCount;
	}

	public void setBossCount(int bossCount) {
		this.bossCount = bossCount;
	}

	public int getWorkerCount() {
		return workerCount;
	}

	public void setWorkerCount(int workerCount) {
		this.workerCount = workerCount;
	}

	public int getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(int tcpPort) {
		this.tcpPort = tcpPort;
	}

	public boolean isKeepAlive() {
		return keepAlive;
	}

	public void setKeepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
	}

	public int getBacklog() {
		return backlog;
	}

	public void setBacklog(int backlog) {
		this.backlog = backlog;
	}

	public int getReaderIdleTime() {
		return readerIdleTime;
	}

	public void setReaderIdleTime(int readerIdleTime) {
		this.readerIdleTime = readerIdleTime;
	}

	public int getWriterIdleTime() {
		return writerIdleTime;
	}

	public void setWriterIdleTime(int writerIdleTime) {
		this.writerIdleTime = writerIdleTime;
	}

	public int getAllIdleTime() {
		return allIdleTime;
	}

	public void setAllIdleTime(int allIdleTime) {
		this.allIdleTime = allIdleTime;
	}

	public void init(Properties properties){
		String serverName = properties.getProperty("netty.serverName");
		String bossCountStr = properties.getProperty("netty.bossCount");
		String workerCountStr = properties.getProperty("netty.workerCount");
		String tcpPortStr = properties.getProperty("netty.tcpPort");
		String keepAliveStr = properties.getProperty("netty.keepAlive");
		String backlogStr = properties.getProperty("netty.backlog");
		String readerIdleTimeStr = properties.getProperty("netty.readerIdleTime");
		String writerIdleTimeStr = properties.getProperty("netty.writerIdleTime");
		String allIdleTimeStr = properties.getProperty("netty.allIdleTime");
		if(serverName!=null&&!"".equals(serverName)) this.setServerName(serverName);
		this.setBossCount(PropertiesUtils.getInt(bossCountStr,2));
		this.setWorkerCount(PropertiesUtils.getInt(workerCountStr,2));
		this.setTcpPort(PropertiesUtils.getInt(tcpPortStr,7998));
		this.setKeepAlive(PropertiesUtils.getBoolean(keepAliveStr));
		this.setBacklog(PropertiesUtils.getInt(backlogStr,2048));
		this.setReaderIdleTime(PropertiesUtils.getInt(readerIdleTimeStr,3600));
		this.setWriterIdleTime(PropertiesUtils.getInt(writerIdleTimeStr,3600));
		this.setAllIdleTime(PropertiesUtils.getInt(allIdleTimeStr,3600));
	}

}
