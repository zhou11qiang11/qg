package com.qg.exception;

public class LoginException extends RuntimeException{
    public static final String WRONG_PHONE = "手机号错误";
    public static final String WRONG_PASSWORD = "手机号和密码不匹配";

    public LoginException(String message){
        super(message);
    }
}
