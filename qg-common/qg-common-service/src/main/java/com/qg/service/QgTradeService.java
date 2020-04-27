package com.qg.service;
import com.qg.pojo.QgTrade;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
* Created by shang-pc on 2015/11/7.
*/
public interface QgTradeService {
    /**
     * 根据支付记录id查询支付记录
     * @param id 支付记录id
     * @return 支付
     * @throws Exception
     */
    public QgTrade getQgTradeById(String id)throws Exception;
    /**
     * 根据条件查询支付记录列表
     * @param param 条件
     * @return 支付记录列表
     * @throws Exception
     */
    public List<QgTrade>	getQgTradeListByMap(Map<String, Object> param)throws Exception;
    /**
     * 根据条件查询支付记录行数
     * @param param 条件
     * @return 支付记录行数
     * @throws Exception
     */
    public Integer getQgTradeCountByMap(Map<String, Object> param)throws Exception;
    /**
     * 前端事务：添加支付记录
     * @param qgTrade 支付记录
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxAddQgTrade(QgTrade qgTrade)throws Exception;
    /**
     * 前端事务：修改支付记录
     * @param qgTrade 支付记录
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxModifyQgTrade(QgTrade qgTrade)throws Exception;
    /**
     * 前端事务：根据支付记录id删除支付
     * @param id 支付记录id
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxDeleteQgTradeById(String id)throws Exception;
    /**
     * 前端事务：根据支付记录ids批量删除支付
     * @param ids 支付记录ids
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxBatchDeleteQgTrade(String ids)throws Exception;

}
