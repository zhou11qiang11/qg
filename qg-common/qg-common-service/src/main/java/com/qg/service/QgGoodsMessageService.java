package com.qg.service;
import com.qg.pojo.QgGoodsMessage;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface QgGoodsMessageService {
    /**
     * 根据商品信息id获取商品信息
     * @param id 商品id
     * @return 商品信息
     * @throws Exception
     */
    public QgGoodsMessage getQgGoodsMessageById(String id)throws Exception;

    /**
     * 根据条件查询商品信息列表
     * @param param 条件
     * @return 商品信息列表
     * @throws Exception
     */
    public List<QgGoodsMessage>	getQgGoodsMessageListByMap(Map<String, Object> param)throws Exception;

    /**
     * 根据条件查询商品信息数量
     * @param param 条件
     * @return 商品信息数量
     * @throws Exception
     */
    public Integer getQgGoodsMessageCountByMap(Map<String, Object> param)throws Exception;

    /**
     * 前端事务：添加商品信息
     * @param qgGoodsMessage 商品信息
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxAddQgGoodsMessage(QgGoodsMessage qgGoodsMessage)throws Exception;

    /**
     * 前端事务：修改商品信息
     * @param qgGoodsMessage 商品信息
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxModifyQgGoodsMessage(QgGoodsMessage qgGoodsMessage)throws Exception;

    /**
     * 前端事务：根据商品信息id删除商品信息
     * @param id 商品信息id
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxDeleteQgGoodsMessageById(String id)throws Exception;

    /**
     * 前端事务：根据商品信息ids批量删除商品信息
     * @param ids 商品信息ids,   1,2,3,4,5,5,6,7,
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxBatchDeleteQgGoodsMessage(String ids)throws Exception;

}
