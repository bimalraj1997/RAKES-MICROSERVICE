package com.microserviceprjct.PaymentService.service;

import com.microserviceprjct.PaymentService.entity.TransactionDetails;
import com.microserviceprjct.PaymentService.model.PaymentRequest;
import com.microserviceprjct.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment details: {}", paymentRequest);

        TransactionDetails transactionDetails= TransactionDetails.builder()
                .amount(paymentRequest.getAmount())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .paymentDate(Instant.now())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .build();
        transactionDetailsRepository.save(transactionDetails);
        log.info("Transaction completed with id: {}",transactionDetails.getId());

        return transactionDetails.getId();
    }
}
