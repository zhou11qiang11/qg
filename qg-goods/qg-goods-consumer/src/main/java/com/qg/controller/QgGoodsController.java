package com.qg.controller;

import com.qg.dto.ReturnResult;
import com.qg.dto.ReturnResultUtils;
import com.qg.service.LocalGoodsService;
import com.qg.utils.Constants;
import com.qg.vo.GoodsVo;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/v")
public class QgGoodsController {

    @Resource
    private LocalGoodsService localGoodsService;

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    private static final ActiveMQQueue QUEUE = new ActiveMQQueue(Constants.MQ_QG_QUEUE);
    private static final Random RD = new Random();

    @GetMapping("/queryGoodsById")
    public ReturnResult queryGoodsById(String id) throws Exception {
        GoodsVo vo = localGoodsService.getGoodsById(id);
        return ReturnResultUtils.returnSuccess(vo);
    }

    @PostMapping("/getGoods")
    public ReturnResult getGoods(@RequestHeader String token, @RequestParam String goodsId) throws Exception {
        String userId = token.split("-")[2];
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("goodsId", goodsId);
        jmsMessagingTemplate.convertAndSend(QUEUE, map);
        return ReturnResultUtils.returnSuccess();
    }

//    public String random(int min, int max) {
//        Integer i = RD.nextInt(max) % (max - min + 1) + min;
//        return i.toString();
//    }

    //抢购结果
    @PostMapping("/flushResult")
    public ReturnResult flushResult(@RequestHeader String token, @RequestParam String goodsId) throws Exception {
        String userId = token.split("-")[2];
        Integer code = localGoodsService.flushResult( userId, goodsId);
        return ReturnResultUtils.returnFail(code,"");
    }

}
