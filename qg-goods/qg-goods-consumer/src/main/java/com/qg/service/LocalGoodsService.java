package com.qg.service;

import com.qg.dto.ReturnResult;
import com.qg.vo.GoodsVo;

public interface  LocalGoodsService {

    GoodsVo getGoodsById(String id) throws Exception;

    //抢购
    ReturnResult getGoods(String userId,String goodsId)throws Exception;
}
