package com.qg.service;

import com.qg.vo.GoodsVo;
import org.apache.activemq.command.ActiveMQMapMessage;

public interface  LocalGoodsService {

    GoodsVo getGoodsById(String id) throws Exception;

    Integer flushResult(String userId,String goodsId) throws Exception;
}
