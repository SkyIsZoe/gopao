package com.zxt.learn.design.delegate.rpc;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;
import java.io.File;

/**
 * Created by zxt on 2019/3/8.
 */
@Configuration
public class AppConfig {

    @Bean(name = "sslContext")
    public SslContext getSsl(){


        File rootFile  = new File(this.getClass().getClassLoader().getResource("sslFile/ca.crt").getPath());
        File certchainFile = new File(this.getClass().getClassLoader().getResource("sslFile/client.crt").getPath());
        File keyFile = new File(this.getClass().getClassLoader().getResource("sslFile/pkcs8_client.key").getPath());

        System.setProperty("https.protocols", "TLSv1");
        SslContext sslCtx=null;
        try {
              sslCtx = SslContextBuilder.forClient().keyManager(certchainFile,keyFile).trustManager(rootFile).build();
        } catch (SSLException e) {
            e.printStackTrace();
        }
        return sslCtx;
    }
}
