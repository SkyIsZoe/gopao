package com.zxt.learn.design.delegate.rpc.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zxt on 2019/3/29.
 */
public class AyscUtils {

    private static ExecutorService cachedThreadPool ;

    static {
        cachedThreadPool = Executors.newCachedThreadPool();
    }

    public static void execute(Runnable r){
        cachedThreadPool.execute(r);
    }

}
