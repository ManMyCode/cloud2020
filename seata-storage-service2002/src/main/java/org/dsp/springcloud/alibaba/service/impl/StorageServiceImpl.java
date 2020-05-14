package org.dsp.springcloud.alibaba.service.impl;

import org.dsp.springcloud.alibaba.dao.StorageMapper;
import org.dsp.springcloud.alibaba.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;
    @Override
    public Integer decrease(Long productId, Integer count) {
        return storageMapper.decrease(productId,count);
    }
}
