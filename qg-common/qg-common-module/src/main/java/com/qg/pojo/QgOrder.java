package com.qg.pojo;

import java.io.Serializable;

/**
 * 
 */
public class QgOrder implements Serializable{

    private String id;
    private String userId;
    private String stockId;
    private String orderNo;
    private String goodsId;
    private Integer num;
    private Double amount;
    private Integer status;
    private String aliTradeNo;
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
    public String getStockId(){
        return this.stockId;
    }
    public void setStockId(String stockId){
        this.stockId=stockId;
    }
    public String getOrderNo(){
        return this.orderNo;
    }
    public void setOrderNo(String orderNo){
        this.orderNo=orderNo;
    }
    public String getGoodsId(){
        return this.goodsId;
    }
    public void setGoodsId(String goodsId){
        this.goodsId=goodsId;
    }
    public Integer getNum(){
        return this.num;
    }
    public void setNum(Integer num){
        this.num=num;
    }
    public Double getAmount(){
        return this.amount;
    }
    public void setAmount(Double amount){
        this.amount=amount;
    }
    public Integer getStatus(){
        return this.status;
    }
    public void setStatus(Integer status){
        this.status=status;
    }
    public String getAliTradeNo(){
        return this.aliTradeNo;
    }
    public void setAliTradeNo(String aliTradeNo){
        this.aliTradeNo=aliTradeNo;
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