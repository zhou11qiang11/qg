package com.qg.service;

import com.alibaba.fastjson.JSON;
import com.qg.pojo.QgUser;
import com.qg.utils.MD5;
import com.qg.utils.RedisUtil;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService{
    @Resource
    private RedisUtil redisUtil;

    public String generateToken(String userAgent, QgUser user) {
        //客户端标识-PHONE-USERID-CREATIONDATE-RANDOM[6位]
        StringBuilder token = new StringBuilder("token:");
        UserAgent ua = UserAgent.parseUserAgentString(userAgent);
        if(ua.getOperatingSystem().isMobileDevice()){
            token.append("MOBILE-");
        }else{
            token.append("PC-");
        }
        token.append(MD5.getMd5(user.getPhone(), 22))
                .append("-");
        token.append(user.getId()).append("-");
        String time = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        token.append(time).append("-");

        token.append(MD5.getMd5(userAgent,6));
        //保存token到redis服务器
        if(ua.getOperatingSystem().isMobileDevice()){
            redisUtil.setStr(token.toString(), JSON.toJSONString(user),24*60L);
        }else{
            redisUtil.setStr(token.toString(), JSON.toJSONString(user),1*60L);
        }
        return token.toString();
    }

    @Override
    public QgUser validateToken(String token) {
        if(token==null || token.isEmpty()){
            return null;
        }
        if(redisUtil.getStr(token)==null){
            return null;
        }
        QgUser user = JSON.parseObject(redisUtil.getStr(token),QgUser.class);
        return user;
    }

    @Override
    public boolean deleteToken(String userAgent, String token) {
        //基于当前useragent加密的字符串
        String ua1 = MD5.getMd5(userAgent,6);
        //基于登录时的useragent加密的字符串
        String ua2 = token.substring(token.length()-6);
        if(!ua1.equals(ua2)){//如果两个加密的字符串不相同，说明更换了设备和浏览器
            return false;
        }
        //判断服务器是否有该token
        if(redisUtil.getStr(token)==null){
            return false;
        }
        //redis自动过期，所以不需要验证有效时间了
        redisUtil.del(token);
        return true;
    }

    @Override
    public String refreshToken(String userAgent, String oldToken) {
        //1.验证token有效
        QgUser user = this.validateToken(oldToken);
        if(user==null){
            return null;
        }
        //2.验证token的剩余时间是否可以置换
        long leftSeconds = redisUtil.getExpire(oldToken);
        if(leftSeconds>=5*60){
            return null;
        }

        //3.生成新的token信息，保存token到redis
        String newToken = this.generateToken(userAgent,user);

        //4.删除之前的token
        boolean flag = this.deleteToken(userAgent,oldToken);

        if(flag==false){
            this.deleteToken(userAgent,newToken);
            return oldToken;
        }

        return newToken;
    }

    /**
     * 获取指定token的剩余有效时间，单位秒
     */
    public long getExpireSecond(String token) {
        if(redisUtil.hasKey(token)){
            return redisUtil.getExpire(token);
        }
        return -1;
    }
}
