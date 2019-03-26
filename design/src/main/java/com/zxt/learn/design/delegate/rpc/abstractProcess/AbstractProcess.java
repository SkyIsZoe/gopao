package com.zxt.learn.design.delegate.rpc.abstractProcess;



import com.zxt.learn.design.delegate.rpc.process.BaseProcess;
import io.netty.channel.ChannelHandler.Sharable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Map;


@Sharable
public abstract class AbstractProcess extends BaseProcess {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractProcess.class);




	/*@Autowired
	@Qualifier("kafkaService")
	protected SendMsgService sendMsg;*/


	//protected RtDataClient rtDataClient;
	
	//@Autowired
	//protected VehicleService vehicleService;
	
	protected ByteBuffer byteBuffer;
	
	protected byte[] byteMsg;
	
	protected String hexMsg;
	//12345
	//protected DataPackage parsedData;
	
	protected int expirySeconds;
	
	
	@Override
	public void init(Map<String, Object> msg) throws Exception {
		//LOG.debug("msg:"+msg);
		super.init(msg);
		this.hexMsg = (String)msg.get(TspMessageKeyword.hexMsg);
		//12345
		/*this.parsedData = (DataPackage)msg.get(TspMessageKeyword.parsedData);*/
		this.byteMsg = (byte[])msg.get(TspMessageKeyword.byteMsg);
		this.byteBuffer = (ByteBuffer)msg.get(TspMessageKeyword.byteBuffer);
		try{
			this.expirySeconds = (int)msg.get(TspMessageKeyword.expirySeconds);
		}catch(Exception e){
			//LOG.debug("abstract is null forward");
		}
	}
	
	@Override
	public void forward() throws Exception {
		
		
	}

}
