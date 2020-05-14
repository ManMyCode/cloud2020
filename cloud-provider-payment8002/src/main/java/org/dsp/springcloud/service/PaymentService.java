package org.dsp.springcloud.service;

import org.dsp.springcloud.entity.Payment;

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}
