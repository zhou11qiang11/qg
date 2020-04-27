package com.qg.interceptor;

import com.alibaba.fastjson.JSON;
import com.qg.dto.ReturnResultUtils;
import com.qg.pojo.QgUser;
import com.qg.utils.Constants;
import com.qg.utils.MD5;
import com.qg.utils.RedisUtil;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LoginTokenIntercepter implements HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String token = request.getHeader("token");
        if(token==null || token.isEmpty()){
            response.setContentType("text/html;charset=utf-8");
            String str = JSON.toJSONString(ReturnResultUtils.returnFail(Constants.STATUS_NO_LOGIN,"请先登录"));
            response.getWriter().print(str);
            return false;
        }
        if(redisUtil.getStr(token)==null){
            response.setContentType("text/html;charset=utf-8");
            String str = JSON.toJSONString(ReturnResultUtils.returnFail(Constants.STATUS_NO_LOGIN,"会话过期，请重新登录"));
            response.getWriter().print(str);
            return false;
        }
        //token有效
        //如果token快过期，置换token
        long leftSeconds = redisUtil.getExpire(token);
        if(leftSeconds>=5*60){//5分钟
            return true;
        }
        //3.生成新的token信息，保存token到redis
        QgUser user = JSON.parseObject(redisUtil.getStr(token), QgUser.class);
        //客户端标识-PHONE-USERID-CREATIONDATE-RANDOM[6位]
        StringBuilder newToken = new StringBuilder("token:");
        UserAgent ua = UserAgent.parseUserAgentString(request.getHeader("user-agent"));
        if(ua.getOperatingSystem().isMobileDevice()){
            newToken.append("MOBILE-");
        }else{
            newToken.append("PC-");
        }
        newToken.append(MD5.getMd5(user.getPhone(), 22)).append("-");
        newToken.append(user.getId()).append("-");
        String time = new SimpleDateFormat("yyyyMMddHHmmss") .format(new Date());
        newToken.append(time).append("-");

        newToken.append(MD5.getMd5(request.getHeader("user-agent"),6));
        //保存token到redis服务器
        if(ua.getOperatingSystem().isMobileDevice()){
            redisUtil.setStr(newToken.toString(), JSON.toJSONString(user),24*60L);
        }else{
            redisUtil.setStr(newToken.toString(), JSON.toJSONString(user),1*60L);
        }

        //4.删除之前的token
        redisUtil.del(token);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
