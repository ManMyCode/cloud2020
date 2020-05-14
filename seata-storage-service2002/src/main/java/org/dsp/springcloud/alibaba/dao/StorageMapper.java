package org.dsp.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageMapper {
    /**
     * 扣减库存
     * @param productId
     * @param count
     * @return
     */
    Integer decrease(@Param("productId")Long productId,@Param("count") Integer count);
}
