package com.qg.pojo;

import java.io.Serializable;

/**
 * 
 */
public class QgGoodsMessage implements Serializable{

    private String id;
    private String userId;
    private String goodsId;
    private String status;
    private Double amount;
    private java.util.Date createdTime;
    private java.util.Date updatedTime;

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getUserId(){
        return this.userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getGoodsId(){
        return this.goodsId;
    }
    public void setGoodsId(String goodsId){
        this.goodsId=goodsId;
    }
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public Double getAmount(){
        return this.amount;
    }
    public void setAmount(Double amount){
        this.amount=amount;
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