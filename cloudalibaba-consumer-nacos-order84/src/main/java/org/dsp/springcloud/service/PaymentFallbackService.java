package org.dsp.springcloud.service;

import org.dsp.springcloud.common.Result;
import org.dsp.springcloud.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public Result<Payment> paymentSQL(Long id) {
        return new Result<>(4444444,"服务降级返回，---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
