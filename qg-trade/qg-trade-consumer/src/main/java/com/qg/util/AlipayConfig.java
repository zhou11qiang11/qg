package com.qg.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@PropertySource(value = "classpath:alipay.properties")
public class AlipayConfig {



    @Resource
    private Environment env;

    @Bean
    public AlipayConfig alipay(){
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.app_id=env.getProperty("app_id");
        alipayConfig.merchant_private_key=env.getProperty("merchant_private_key");
        alipayConfig.alipay_public_key=env.getProperty("alipay_public_key");
        alipayConfig.notify_url=env.getProperty("notify_url");
        alipayConfig.return_url=env.getProperty("return_url");
        alipayConfig.sign_type=env.getProperty("sign_type");
        alipayConfig.charset=env.getProperty("charset");
        alipayConfig.gatewayUrl=env.getProperty("gatewayUrl");
        alipayConfig.log_path=env.getProperty("log_path");
        return alipayConfig;
    }
    public static String app_id;
    public static String merchant_private_key;
    public static String alipay_public_key;
    public static String notify_url;
    public static String return_url;
    public static String sign_type;
    public static String charset;
    public static String gatewayUrl;
    public static String log_path;


}
