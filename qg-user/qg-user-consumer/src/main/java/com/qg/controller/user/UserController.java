package com.qg.controller.user;

import com.alibaba.fastjson.JSON;
import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.qg.dto.ReturnResult;
import com.qg.dto.ReturnResultUtils;
import com.qg.exception.LoginException;
import com.qg.pojo.QgUser;
import com.qg.service.QgUserService;
import com.qg.service.TokenService;
import com.qg.utils.RedisUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class UserController {

    @Autowired
    private CCPRestSmsSDK ccpRestSmsSDK;
    @Autowired
    private RedisUtil redisUtil;
    @Reference
    private QgUserService qgUserService;

    @Autowired
    //@Resource
    private TokenService tokenService;


    @RequestMapping("/api/sendsmscode")
    @ResponseBody
    public ReturnResult sendSMSCode(String phone){
        String validCode =  new Random().nextInt(9000)+1000+"";
        HashMap<String, Object> result = ccpRestSmsSDK.sendTemplateSMS(phone,"1",new String[]{validCode, "1"});
        if("000000".equals(result.get("statusCode"))){
            //保存到redis缓存中
            redisUtil.setStr("smscode:"+phone,validCode,60L);
            return ReturnResultUtils.returnSuccess();
        }else{
            return ReturnResultUtils.returnFail(10,"发送验证码失败");
        }
    }

    @RequestMapping("/api/checksmscode")
    @ResponseBody
    public ReturnResult checksmscode(String phone,String smscode) {
        //检查验证码
        String correctsmscode = redisUtil.getStr("smscode:" + phone);
        if (correctsmscode == null || !correctsmscode.equals(smscode)) {
            return ReturnResultUtils.returnFail(10, "验证码失效或错误");
        } else {
            redisUtil.del("smscode:" + phone);
            //保存到数据库

            return ReturnResultUtils.returnSuccess();
        }
    }
    @RequestMapping("/api/regist")
    @ResponseBody
    public ReturnResult regist(QgUser user) {
        try {
            int count = qgUserService.qdtxAddQgUser(user);
            if(count>0){
                return ReturnResultUtils.returnSuccess();
            }else{
                return ReturnResultUtils.returnFail(10, "保存失败");
            }
        }catch (Exception e){
            return ReturnResultUtils.returnFail(10, "服务器错误");
        }
    }

    @RequestMapping("/api/doLogin")
    @ResponseBody
    public ReturnResult login(String phone, String password, HttpServletRequest request){
        try {
            QgUser user = qgUserService.queryQgUserByPhone(phone);
            if(user==null){
                return ReturnResultUtils.returnFail(10, "手机号码不正确");
            }else if(!user.getPassword().equals(password)){
                return ReturnResultUtils.returnFail(10, "手机号和密码不匹配");
            }
            //登录成功
            //登录信息保存到redis服务器，生成token
            String token = tokenService.generateToken(request.getHeader("user-agent"),user);
            //封装返回数据
            Map data = new HashMap();
            data.put("token",token);
            if(token.startsWith("MOBILE-")){
                data.put("expire-time",24*60*60*1000L);
            }else{
                data.put("expire-time",1*60*60*1000L);
            }
            data.put("gen-time",System.currentTimeMillis());
            return ReturnResultUtils.returnSuccess(data);
        } catch(Exception e){
            return ReturnResultUtils.returnFail(10, e.getMessage());
        }
    }

    @RequestMapping("/api/logout")
    @ResponseBody
    public ReturnResult logout(HttpServletRequest request){
       boolean flag = tokenService.deleteToken(request.getHeader("user-agent"), request.getHeader("token"));
       if(flag){
           return ReturnResultUtils.returnSuccess();
       }else{
           return ReturnResultUtils.returnFail(10,"退出登录失败");
       }
    }

}
