package org.dsp.springcloud.alibaba.service;

public interface StorageService {
    /**
     * 扣减库存
     * @param productId
     * @param count
     * @return
     */
    Integer decrease(Long productId,Integer count);
}
