package com.mustunal.transactionproducer.controllers;

import com.mustunal.model.Transaction;
import com.mustunal.transactionproducer.service.TransactionProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionProducerService transactionProducerService;

    @PostMapping
    public String createTransaction(@RequestBody Transaction transaction){
        return transactionProducerService.publishTransaction(transaction);
    }
}
