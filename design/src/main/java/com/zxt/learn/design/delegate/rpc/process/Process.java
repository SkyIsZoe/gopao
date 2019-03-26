package com.zxt.learn.design.delegate.rpc.process;

import java.util.Map;

public interface Process
{
     void init(Map<String, Object> msg) throws Exception;
    
    //public void readMsg() throws Exception;
    
     void parse() throws Exception;
    
     void reply() throws Exception;
    
     void forward() throws Exception;
    
     boolean getIsContinue();
    
    
}
