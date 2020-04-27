package com.qg.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class IntercepterConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private CORSInterceptor corsInterceptor;
    @Autowired
    private LoginTokenIntercepter tokenIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor).addPathPatterns("/api/**");
//        registry.addInterceptor(tokenIntercepter)
//                .addPathPatterns("/api/**")//要拦截的url
//                .excludePathPatterns("/api/sendsmscode","/api/checksmscode","/api/regist","/api/doLogin");//从拦截的url中要排除的url
    }
}
