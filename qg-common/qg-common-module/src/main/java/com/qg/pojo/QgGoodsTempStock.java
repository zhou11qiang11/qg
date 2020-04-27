package com.qg.pojo;

import java.io.Serializable;

/**
 * 
 */
public class QgGoodsTempStock implements Serializable{

    private String id;
    private String goodsId;
    private String userId;
    private Integer status;
    private java.util.Date createdTime;
    private java.util.Date updatedTime;

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getGoodsId(){
        return this.goodsId;
    }
    public void setGoodsId(String goodsId){
        this.goodsId=goodsId;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public Integer getStatus(){
        return this.status;
    }
    public void setStatus(Integer status){
        this.status=status;
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