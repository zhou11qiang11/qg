package com.qg.pojo;

import java.io.Serializable;

/**
 * 
 */
public class QgTrade implements Serializable{

    private String id;
    private String orderNo;
    private String tradeNo;
    private Integer payMethod;
    private Double amount;
    private java.util.Date createdTime;
    private java.util.Date updatedTime;

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getOrderNo(){
        return this.orderNo;
    }
    public void setOrderNo(String orderNo){
        this.orderNo=orderNo;
    }
    public String getTradeNo(){
        return this.tradeNo;
    }
    public void setTradeNo(String tradeNo){
        this.tradeNo=tradeNo;
    }
    public Integer getPayMethod(){
        return this.payMethod;
    }
    public void setPayMethod(Integer payMethod){
        this.payMethod=payMethod;
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