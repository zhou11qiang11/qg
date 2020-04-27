package com.qg.mapper.user;

import com.qg.pojo.QgUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QgUserMapper {

    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @return 用户
     * @throws Exception
     */
    public QgUser getQgUserById(String id)throws Exception;
    /**
     * 根据条件查询用户列表
     * @param param 条件: 可以包含如下key：（id,phone, password,wxUserId,realName,orderby,from,to）
     * @return 用户列表
     * @throws Exception
     */
    public List<QgUser> getQgUserListByMap(Map<String, Object> param)throws Exception;
    /**
     * 根据条件查询用户行数
     * @param param 条件
     * @return 用户行数
     * @throws Exception
     */
    public Integer getQgUserCountByMap(Map<String, Object> param)throws Exception;
    /**
     * 前端事务：添加用户
     * @param qgUser 用户
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxAddQgUser(QgUser qgUser)throws Exception;
    /**
     * 前端事务：修改用户
     * @param qgUser 用户
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxModifyQgUser(QgUser qgUser)throws Exception;
    /**
     * 前端事务：根据用户id删除用户
     * @param id 用户id
     * @return 影响行数
     * @throws Exception
     */
    public Integer qdtxDeleteQgUserById(String id)throws Exception;

    /***
     * 根据手机号和密码来查询用户
     * @return 用户
     * @throws Exception
     */
    public QgUser queryQgUserByPhone(String phone)throws Exception;

}
