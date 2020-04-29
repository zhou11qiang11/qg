package com.qg.service;

import com.qg.mapper.QgGoodsTempStockMapper;
import com.qg.pojo.QgGoodsTempStock;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(version = "1.0.0")
public class QgGoodsTempStockMapperImpl implements QgGoodsTempStockService{

    @Resource
    private QgGoodsTempStockMapper qgGoodsTempStockMapper;

    @Override
    public QgGoodsTempStock getQgGoodsTempStockById(String id) throws Exception {
        return null;
    }

    @Override
    public List<QgGoodsTempStock> getQgGoodsTempStockListByMap(Map<String, Object> param) throws Exception {
        return null;
    }

    @Override
    public Integer getQgGoodsTempStockCountByMap(Map<String, Object> param) throws Exception {
        return null;
    }

    @Override
    public Integer qdtxAddQgGoodsTempStock(QgGoodsTempStock qgGoodsTempStock) throws Exception {
        int count=  qgGoodsTempStockMapper.insert(qgGoodsTempStock);
        if (count>0){
            return Integer.parseInt(qgGoodsTempStock.getId());
        }
        return -1;
    }

    @Override
    public Integer qdtxModifyQgGoodsTempStock(QgGoodsTempStock qgGoodsTempStock) throws Exception {
        return qgGoodsTempStockMapper.update(qgGoodsTempStock);
    }

    @Override
    public Integer qdtxDeleteQgGoodsTempStockById(String id) throws Exception {
        return null;
    }

    @Override
    public Integer qdtxBatchDeleteQgGoodsTempStock(String ids) throws Exception {
        return null;
    }


}
