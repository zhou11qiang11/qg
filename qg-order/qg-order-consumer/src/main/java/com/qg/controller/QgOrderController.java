package com.qg.controller;

import com.qg.dto.ReturnResult;
import com.qg.dto.ReturnResultUtils;
import com.qg.pojo.QgOrder;
import com.qg.service.QgOrderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QgOrderController {

    @Reference(version = "1.0.0")
    private QgOrderService qgOrderService;

    @RequestMapping("/api/v/queryOrderList")
    public ReturnResult queryOrderList(@RequestHeader String token) throws Exception {
        String userId = token.split("-")[2];
        Map pararm = new HashMap();
        pararm.put("userId",userId);
       List<QgOrder> list= qgOrderService.getQgOrderListByMap(pararm);
        return ReturnResultUtils.returnSuccess(list);
    }


}
