package com.qg.pojo;

import java.io.Serializable;

/**
 * 商品
 */
public class QgGoods implements Serializable{

    private Long id;
    private String goodsImg;
    private String goodsName;
    private Double price;
    private Integer stock;
    private java.util.Date createdTime;
    private java.util.Date updatedTime;

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getGoodsImg(){
        return this.goodsImg;
    }
    public void setGoodsImg(String goodsImg){
        this.goodsImg=goodsImg;
    }
    public String getGoodsName(){
        return this.goodsName;
    }
    public void setGoodsName(String goodsName){
        this.goodsName=goodsName;
    }
    public Double getPrice(){
        return this.price;
    }
    public void setPrice(Double price){
        this.price=price;
    }
    public Integer getStock(){
        return this.stock;
    }
    public void setStock(Integer stock){
        this.stock=stock;
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