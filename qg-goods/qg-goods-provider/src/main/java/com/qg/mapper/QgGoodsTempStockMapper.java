package com.qg.mapper;

import com.qg.pojo.QgGoodsTempStock;
import com.qg.vo.GoodsVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QgGoodsTempStockMapper {

    Integer insert(QgGoodsTempStock qgGoodsTempStock);

    Integer update(QgGoodsTempStock qgGoodsTempStock) ;
}
