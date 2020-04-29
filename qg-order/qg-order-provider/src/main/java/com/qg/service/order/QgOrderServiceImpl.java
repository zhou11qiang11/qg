package com.qg.service.order;

import com.qg.mapper.order.QgOrderMapper;
import com.qg.pojo.QgOrder;
import com.qg.service.QgOrderService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(version = "1.0.0")
@Component
public class QgOrderServiceImpl implements QgOrderService {
    @Resource
    private QgOrderMapper qgOrderMapper;

    @Override
    public QgOrder getQgOrderById(String id) throws Exception {
        return qgOrderMapper.getQgOrderById(id);
    }

    @Override
    public List<QgOrder> getQgOrderListByMap(Map<String, Object> param) throws Exception {
        return qgOrderMapper.getQgOrderListByMap(param);
    }

    @Override
    public Integer getQgOrderCountByMap(Map<String, Object> param) throws Exception {
        return qgOrderMapper.getQgOrderCountByMap(param);
    }

    @Override
    public Integer qdtxAddQgOrder(QgOrder qgOrder) throws Exception {
        return qgOrderMapper.qdtxAddQgOrder(qgOrder);
    }

    @Override
    public Integer qdtxModifyQgOrder(QgOrder qgOrder) throws Exception {
        return qgOrderMapper.qdtxModifyQgOrder(qgOrder);
    }

    @Override
    public Integer qdtxDeleteQgOrderById(String id) throws Exception {
        return qgOrderMapper.qdtxDeleteQgOrderById(id);
    }

    @Override
    public Integer qdtxBatchDeleteQgOrder(String ids) throws Exception {
        return null;
    }

    @Override
    public QgOrder queryQgOrderByNo(String orderNo) throws Exception {
        return qgOrderMapper.queryQgOrderByNo(orderNo);
    }

    public List<QgOrder> queryPayTimeoutOrder() throws Exception {
        return qgOrderMapper.queryPayTimeoutOrder();
    }
}
