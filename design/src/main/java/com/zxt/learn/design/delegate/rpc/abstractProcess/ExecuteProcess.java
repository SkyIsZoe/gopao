package com.zxt.learn.design.delegate.rpc.abstractProcess;

import com.zxt.learn.design.delegate.rpc.MethodHandler;
import com.zxt.learn.design.delegate.rpc.messge.MessageRequest;
import com.zxt.learn.design.delegate.rpc.process.Process;
import com.zxt.learn.design.delegate.rpc.utils.AyscUtils;
import com.zxt.learn.design.delegate.rpc.utils.MappingUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by zxt on 2019/3/29.
 */
public class ExecuteProcess implements Process{

    private MessageRequest request;

    @Override
    public void init(Map<String, Object> msg) throws Exception {
        MessageRequest request = (MessageRequest)msg.get("request");
        this.request = request;
    }

    @Override
    public void parse() throws Exception {
        if(request!=null){
            AyscUtils.execute(new Thread(()->{
                Class[] classes = request.getTypeParameters();
                String className = request.getClassName();
                String methodNmae = request.getMethodName();
                Object [] obs = request.getParameters();
                StringBuilder sb = new StringBuilder();
                sb.append(className).append(".").append(methodNmae).append(":");
                for(Class cla:classes){
                    sb.append(cla.getSimpleName()).append(",");
                }
                if(sb.length()>0){
                    sb.deleteCharAt(sb.length()-1);
                }
                MethodHandler methodHandler = MappingUtils.getMethodHandler(sb.toString());
                Method method = methodHandler.getMethod();
                Object obj = methodHandler.getHandler();
                try {
                    method.invoke(obj,obs);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }));
        }
    }

    @Override
    public void reply() throws Exception {

    }

    @Override
    public void forward() throws Exception {

    }

    @Override
    public boolean getIsContinue() {
        return false;
    }
}
