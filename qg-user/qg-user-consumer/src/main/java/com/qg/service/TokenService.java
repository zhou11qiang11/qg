package com.qg.service;

import com.qg.pojo.QgUser;

public interface TokenService {

    /**
     * 根据user-agent, 用户信息，生成token
     */
    String generateToken(String userAgent, QgUser user);

    /**
     * 验证token是否有效
     */
    QgUser validateToken(String token);

    /**
     * 销毁token
     */
    boolean deleteToken(String userAgent, String token);


    /**
     * 置换快过期的token
     */
    String refreshToken(String userAgent, String oldToken);



    /**
     * 获取指定token的剩余有效时间，单位秒
     */
    long getExpireSecond(String token);


}
