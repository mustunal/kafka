package com.mustunal.transactionproducer.service;

import com.mustunal.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class TransactionProducerService {

    @Value("${topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    public String publishTransaction(Transaction transaction){
        ListenableFuture<SendResult<String, Transaction>> sendResult = kafkaTemplate.send(topicName, transaction);

        sendResult.addCallback(new ListenableFutureCallback<SendResult<String, Transaction>>() {
            @Override
            public void onSuccess(SendResult<String, Transaction> result) {
                System.out.println("Sent message=[" + transaction +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + transaction + "] due to : " + ex.getMessage());
            }
        });

        return "Transaction published!";
    }
}
