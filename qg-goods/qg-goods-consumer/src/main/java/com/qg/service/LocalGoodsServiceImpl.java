package com.qg.service;

import com.alibaba.fastjson.JSON;
import com.qg.dto.ReturnResult;
import com.qg.dto.ReturnResultUtils;
import com.qg.pojo.QgGoods;
import com.qg.utils.Constants;
import com.qg.utils.RedisUtil;
import com.qg.vo.GoodsVo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LocalGoodsServiceImpl implements LocalGoodsService {

    @Reference(version = "1.0.0",retries = 0,timeout = 10000)
    private QgGoodsService qgGoodsService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public GoodsVo getGoodsById(String id) throws Exception {
        //先从redis中查询
        if (redisUtil.hasKey(Constants.KEY_GOODS + id)) {
            //如果存在则从缓存中取出
            GoodsVo vo = JSON.parseObject(redisUtil.getStr(Constants.KEY_GOODS + id), GoodsVo.class);
            return vo;
        }
        //如果不存在,从数据库查出,保存到缓存中
        //使用互斥锁防止缓存击穿,先抢锁,抢到锁才能查询
        if (!redisUtil.lock(Constants.KEY_LOCK_GOODS_LOAD + id, 2l)) {
            //如果没抢到锁,100毫秒后,继续抢
            Thread.sleep(100);
            return getGoodsById(id);
        }
        QgGoods goods = qgGoodsService.getQgGoodsById(id);
        GoodsVo vo = new GoodsVo();
        vo.setId(goods.getId().toString());
        vo.setGoodsImg(goods.getGoodsImg());
        vo.setGoodsName(goods.getGoodsName());
        vo.setPrice(goods.getPrice());
        vo.setStock(goods.getStock());
        vo.setCurrentStock(goods.getStock());
        //存入缓存,通过随机过期时间解决缓存雪崩问题
        redisUtil.setStr(Constants.KEY_GOODS + id, JSON.toJSONString(vo), redisUtil.random(Constants.MAX_MINUTES, Constants.MIN_MINUTES));
        //释放锁
        redisUtil.del(Constants.KEY_LOCK_GOODS_LOAD + id);
        return vo;
    }

    @Override
    public ReturnResult getGoods(String userId, String goodsId) throws Exception {
        //判断userId是否买过goodsId;
        if(redisUtil.hhasKey(Constants.KEY_QG_RESULT+goodsId,userId)){
            return ReturnResultUtils.returnFail(Constants.STATUS_QG_FAIL,"对不起,您已抢购成功,请勿重复下单");
        }
        GoodsVo vo = getGoodsById(goodsId);
        //判断库存数量是否足够
        Long selled = redisUtil.hsize(Constants.KEY_QG_RESULT+goodsId);
        if (vo.getStock()-selled<=0){
            return ReturnResultUtils.returnFail(Constants.STATUS_QG_FAIL,"对不起,本次抢购商品已全部售出,感谢本次参与");
        }
        //记录购买信息:先保存到缓存中,再保存到数据库里
        //在统计该商品已卖出数量时需要用goodsId查询
        redisUtil.hset(Constants.KEY_QG_RESULT+goodsId,userId,"1");
        //生成订单

        //购买成功
        return ReturnResultUtils.returnSuccess();
    }
}
