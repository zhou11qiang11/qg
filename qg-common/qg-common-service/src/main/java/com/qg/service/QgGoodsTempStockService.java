package com.qg.service;
import com.qg.pojo.QgGoodsTempStock;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
* Created by shang-pc on 2015/11/7.
*/

public interface QgGoodsTempStockService {
    /**
     * 根据商品临时库存id查询商品临时库存
     * @param id 商品临时库存id
     * @return 商品临时库存
     * @throws Exception
     */
    public QgGoodsTempStock getQgGoodsTempStockById(String id)throws Exception;
    /**
     * 根据条件查询商品临时库存列表
     * @param param 条件
     * @return 商品临时库存列表
     * @throws Exception
     */
    public List<QgGoodsTempStock>	getQgGoodsTempStockListByMap(Map<String, Object> param)throws Exception;
    /**
     * 根据条件查询商品临时库存行数
     * @param param 条件
     * @return 商品临时库存行数
     * @throws Exception
     */
    public Integer getQgGoodsTempStockCountByMap(Map<String, Object> param)throws Exception;
    /**
     * 前端事务：添加商品临时库存
     * @param qgGoodsTempStock 商品临时库存
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxAddQgGoodsTempStock(QgGoodsTempStock qgGoodsTempStock)throws Exception;
    /**
     * 前端事务：修改商品临时库存
     * @param qgGoodsTempStock 商品临时库存
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxModifyQgGoodsTempStock(QgGoodsTempStock qgGoodsTempStock)throws Exception;
    /**
     * 前端事务：根据商品临时库存id删除商品临时库存
     * @param id 商品临时库存id
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxDeleteQgGoodsTempStockById(String id)throws Exception;
    /**
     * 前端事务：根据商品临时库存ids批量删除商品临时库存
     * @param ids 商品临时库存ids
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxBatchDeleteQgGoodsTempStock(String ids)throws Exception;

}
