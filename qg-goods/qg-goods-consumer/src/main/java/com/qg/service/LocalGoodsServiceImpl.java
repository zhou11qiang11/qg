package com.qg.service;

import com.alibaba.fastjson.JSON;
import com.qg.dto.ReturnResult;
import com.qg.dto.ReturnResultUtils;
import com.qg.pojo.QgGoods;
import com.qg.pojo.QgGoodsTempStock;
import com.qg.pojo.QgOrder;
import com.qg.utils.Constants;
import com.qg.utils.IdWorker;
import com.qg.utils.RedisUtil;
import com.qg.vo.GoodsVo;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class LocalGoodsServiceImpl implements LocalGoodsService {

    @Reference(version = "1.0.0", retries = 0, timeout = 10000)
    private QgGoodsService qgGoodsService;
    @Reference(version = "1.0.0", retries = 0, timeout = 10000)
    private QgGoodsTempStockService qgGoodsTempStockService;
    @Reference(version = "1.0.0", retries = 0, timeout = 10000)
    private QgOrderService qgOrderService;

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
        while (!redisUtil.lock(Constants.KEY_LOCK_GOODS_LOAD + id, 1l)) {
            //如果没抢到锁,30毫秒后,继续抢
            Thread.sleep(30);
//            return getGoodsById(id);
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

    @JmsListener(destination =  Constants.MQ_QG_QUEUE)
    public void getGoods(ActiveMQMapMessage msg) throws Exception {
        String userId = msg.getString("userId");
        String goodsId = msg.getString("goodsId");
        //判断userId是否买过goodsId;
        if (redisUtil.hhasKey(Constants.KEY_QG_RESULT + goodsId, userId)) {
            return;// ReturnResultUtils.returnFail(Constants.STATUS_QG_FAIL, "对不起,您已抢购成功,请勿重复下单");
        }
        //购买商品需要获取到商品id和用户id;
        GoodsVo vo = getGoodsById(goodsId);
        //判断库存数量是否足够
        Long selled = redisUtil.hsize(Constants.KEY_QG_RESULT + goodsId);
        if (vo.getStock() - selled <= 0) {
            return;// ReturnResultUtils.returnFail(Constants.STATUS_QG_FAIL, "对不起,本次抢购商品已全部售出,感谢本次参与");
        }
        //执行购买操作,先抢锁,抢到了才能执行,防止超卖
        while (!redisUtil.lock(Constants.KEY_LOCK_GOODS_QG + goodsId, 1l)) {
            Thread.sleep(20);
        }
        try {
            //抢完锁再次判断库存数量是否足够
            selled = redisUtil.hsize(Constants.KEY_QG_RESULT + goodsId);
            if (vo.getStock() - selled <= 0) {
                return;// ReturnResultUtils.returnFail(Constants.STATUS_QG_FAIL, "对不起,本次抢购商品已全部售出,感谢本次参与");
            }
            //记录购买信息:先保存到缓存中,再保存到数据库里
            //在判断是否购买过商品时,需要用userId和goodsId查询
            //在统计该商品已卖出数量时需要用goodsId查询
            redisUtil.hset(Constants.KEY_QG_RESULT + goodsId, userId, "1");
            //临时保存信息到tempstock表
            QgGoodsTempStock tempStock = new QgGoodsTempStock();
            tempStock.setStatus(0);
            tempStock.setCreatedTime(new Date());
            tempStock.setUserId(userId);
            tempStock.setGoodsId(goodsId);
            Integer stockId = qgGoodsTempStockService.qdtxAddQgGoodsTempStock(tempStock);
            //生成订单
            QgOrder qgOrder = new QgOrder();
            qgOrder.setStatus(0);
            qgOrder.setNum(1);
            qgOrder.setUserId(userId);
            qgOrder.setOrderNo(IdWorker.getId());
            qgOrder.setAmount(vo.getPrice() * 1);
            qgOrder.setGoodsId(goodsId);
            qgOrder.setCreatedTime(new Date());
            //回填完通过return传值
            qgOrder.setStockId(stockId.toString());
            qgOrderService.qdtxAddQgOrder(qgOrder);
        } finally {
            //释放锁
            redisUtil.unLock(Constants.KEY_LOCK_GOODS_QG + goodsId);
        }
        //购买成功
        //return ReturnResultUtils.returnSuccess();
    }

    @Override
    public Integer flushResult(String userId, String goodsId) throws Exception {
        if(redisUtil.hhasKey(Constants.KEY_QG_RESULT+goodsId,userId)){
            return 0;//表示成功
        } else {
            return 1102;//表示失败
        }
    }

}
