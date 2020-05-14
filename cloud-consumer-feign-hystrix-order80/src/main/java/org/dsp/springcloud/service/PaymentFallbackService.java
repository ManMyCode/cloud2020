package org.dsp.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentOk(Integer id) {
        return "PaymentFallbackService fall back ok";
    }

    @Override
    public String paymentTimeout(Integer id) {
        return "PaymentFallbackService fall back timeout";
    }
}
