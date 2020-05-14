package org.dsp.springcloud.service;

import org.dsp.springcloud.common.Result;
import org.dsp.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping("/paymentSQL/{id}")
    public Result<Payment> paymentSQL(@PathVariable("id") Long id);
}
