package com.qg.config;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration     //java config
public class SmsConfig {
    @Bean
    public CCPRestSmsSDK ccpRestSmsSDK(){
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init("app.cloopen.com", "8883");
        sdk.setAccount("8a216da86f9cc12f01701091c0102a29", "7c3e7b8b92d444fe962996a465f4ac85");
        sdk.setAppId("8a216da86f9cc12f01701091c06c2a2f");
        sdk.setBodyType(BodyType.Type_JSON);
        return sdk;
    }


}
