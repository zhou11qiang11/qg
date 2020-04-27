package com.qg.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//@Data
public class QgTask implements Serializable {
    private Integer id;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
    private Integer taskType;
    private String  mqExchange;
    private String mqRoutingKey;
    private String requestBody;
    private Integer version;
    private Integer status;
    private String  errorMsg ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getMqExchange() {
        return mqExchange;
    }

    public void setMqExchange(String mqExchange) {
        this.mqExchange = mqExchange;
    }

    public String getMqRoutingKey() {
        return mqRoutingKey;
    }

    public void setMqRoutingKey(String mqRoutingKey) {
        this.mqRoutingKey = mqRoutingKey;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
