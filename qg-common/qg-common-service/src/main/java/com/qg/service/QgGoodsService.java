package com.qg.service;
import com.qg.pojo.QgGoods;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface QgGoodsService {

    /**
     * 根据商品id查询商品
     * @param id 商品id
     * @return 商品
     * @throws Exception
     */
    public QgGoods getQgGoodsById(String id)throws Exception;

    /**
     * 根据条件查询商品列表
     * @param param 条件
     * @return 商品列表
     * @throws Exception
     */
    public List<QgGoods> getQgGoodsListByMap(Map<String, Object> param)throws Exception;

    /**
     * 根据条件查询商品数量
     * @param param 条件
     * @return 商品数量
     * @throws Exception
     */
    public Integer getQgGoodsCountByMap(Map<String, Object> param)throws Exception;

    /**
     * 前端事务：添加商品
     * @param qgGoods 商品
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxAddQgGoods(QgGoods qgGoods)throws Exception;

    /**
     * 前端事务：修改商品
     * @param qgGoods 商品
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxModifyQgGoods(QgGoods qgGoods)throws Exception;

    /**
     * 前端事务：根据商品id删除商品
     * @param id 商品id
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxDeleteQgGoodsById(String id)throws Exception;
    /**
     * 前端事务：根据商品ids批量删除商品
     * @param ids 商品ids
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxBatchDeleteQgGoods(String ids)throws Exception;

}
