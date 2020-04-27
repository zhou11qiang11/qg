package com.qg.pojo;

import java.io.Serializable;

/**
 * 用户表
 */
public class QgUser implements Serializable{

    private String id;
    private String phone;
    private String password;
    private String wxUserId;
    private String realName;
    private java.util.Date createdTime;
    private java.util.Date updatedTime;

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getWxUserId(){
        return this.wxUserId;
    }
    public void setWxUserId(String wxUserId){
        this.wxUserId=wxUserId;
    }
    public String getRealName(){
        return this.realName;
    }
    public void setRealName(String realName){
        this.realName=realName;
    }
    public java.util.Date getCreatedTime(){
        return this.createdTime;
    }
    public void setCreatedTime(java.util.Date createdTime){
        this.createdTime=createdTime;
    }
    public java.util.Date getUpdatedTime(){
        return this.updatedTime;
    }
    public void setUpdatedTime(java.util.Date updatedTime){
        this.updatedTime=updatedTime;
    }
}