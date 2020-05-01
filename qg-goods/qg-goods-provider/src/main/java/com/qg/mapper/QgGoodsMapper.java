package com.qg.mapper;

import com.qg.pojo.QgGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QgGoodsMapper {
    @Select("select * from qg_goods where id=#{id}")
    QgGoods getGoodsById(@Param("id")String id);

    Integer qdtxModifyQgGoods(@Param("id") Integer id, @Param("stock") Integer stock);
}
