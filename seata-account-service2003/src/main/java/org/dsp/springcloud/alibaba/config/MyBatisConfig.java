package org.dsp.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"org.dsp.springcloud.alibaba.dao"})
public class MyBatisConfig {

}
