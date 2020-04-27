package com.qg.service;
import com.qg.pojo.QgOrder;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface QgOrderService {
    /**
     * 根据订单id查询订单
     * @param id 订单id
     * @return 订单
     * @throws Exception
     */
    public QgOrder getQgOrderById(String id)throws Exception;
    /**
     * 根据条件查询订单列表
     * @param param 条件
     * @return 订单列表
     * @throws Exception
     */
    public List<QgOrder>	getQgOrderListByMap(Map<String, Object> param)throws Exception;
    /**
     * 根据条件查询订单行数
     * @param param 条件
     * @return 订单行数
     * @throws Exception
     */
    public Integer getQgOrderCountByMap(Map<String, Object> param)throws Exception;
    /**
     * 前端事务：添加订单
     * @param qgOrder 订单
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxAddQgOrder(QgOrder qgOrder)throws Exception;
    /**
     * 前端事务：修改订单
     * @param qgOrder 订单
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxModifyQgOrder(QgOrder qgOrder)throws Exception;
    /**
     * 前端事务：根据订单id删除订单
     * @param id 商品临时库存id
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxDeleteQgOrderById(String id)throws Exception;
    /**
     * 前端事务：根据订单ids批量删除订单
     * @param ids 订单ids
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxBatchDeleteQgOrder(String ids)throws Exception;

    /**
     * 根据订单编号查询订单
     * @param orderNo 订单编号
     * @return 订单
     * @throws Exception
     */
    public QgOrder queryQgOrderByNo(String orderNo)throws Exception;

    /**
     * 查询所有状态为未支付，并且创建时间是30分钟之前的订单
     */
    public List<QgOrder> queryPayTimeoutOrder() throws Exception;
}
