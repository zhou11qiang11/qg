package com.qg.service;

import com.qg.mapper.QgGoodsMapper;
import com.qg.pojo.QgGoods;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service(version = "1.0.0")
public class QgGoodsServiceImpl implements QgGoodsService{

    @Resource
    private QgGoodsMapper qgGoodsMapper;

    @Override
    public QgGoods getQgGoodsById(String id) throws Exception {
        return qgGoodsMapper.getGoodsById(id);
    }

    @Override
    public List<QgGoods> getQgGoodsListByMap(Map<String, Object> param) throws Exception {
        return null;
    }

    @Override
    public Integer getQgGoodsCountByMap(Map<String, Object> param) throws Exception {
        return null;
    }

    @Override
    public Integer qdtxAddQgGoods(QgGoods qgGoods) throws Exception {
        return null;
    }

    @Override
    public Integer qdtxModifyQgGoods(QgGoods qgGoods) throws Exception {
        return qgGoodsMapper.qdtxModifyQgGoods(qgGoods.getId().intValue(),qgGoods.getStock());
    }

    @Override
    public Integer qdtxDeleteQgGoodsById(String id) throws Exception {
        return null;
    }

    @Override
    public Integer qdtxBatchDeleteQgGoods(String ids) throws Exception {
        return null;
    }
}
