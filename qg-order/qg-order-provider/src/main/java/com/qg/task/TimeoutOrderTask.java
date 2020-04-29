package com.qg.task;

import com.qg.mapper.order.QgOrderMapper;
import com.qg.pojo.QgGoodsTempStock;
import com.qg.pojo.QgOrder;
import com.qg.service.QgGoodsTempStockService;
import com.qg.service.QgOrderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
public class TimeoutOrderTask {
    @Resource
    private QgOrderService qgOrderService;
    @Reference(version = "1.0.0")
    private QgGoodsTempStockService qgGoodsTempStockService;

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void timeoutOrder(){
        System.out.println("--扫描支付超时订单--");
        try {
            //订单修改为超时,status=2;
            List<QgOrder> list = qgOrderService.queryPayTimeoutOrder();
            for (QgOrder order:list){
                System.out.println("--取消id是"+order.getId()+"的订单--");
                order.setStatus(2);
                qgOrderService.qdtxModifyQgOrder(order);
                System.out.println("--取消id是"+order.getId()+"的锁定临时库存--");
                QgGoodsTempStock tempStock = new QgGoodsTempStock();
                tempStock.setId(order.getStockId());
                tempStock.setStatus(2);
                qgGoodsTempStockService.qdtxModifyQgGoodsTempStock(tempStock);
            }
            System.out.println("--本次扫描结束,共取消"+list.size()+"个订单---");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
