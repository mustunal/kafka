package com.mustunal.transactionconsumer.service;

import com.mustunal.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumerService {

    @KafkaListener(
            topics = "${topic.name}",
            containerFactory= "listenerContainerFactory"
    )
    /*@KafkaListener(
            containerFactory= "listenerContainerFactory",
           topicPartitions = @TopicPartition( topic = "${topic.name}",
           partitionOffsets = {
                   @PartitionOffset(partition = "1", initialOffset = "0"),
                   @PartitionOffset(partition = "2", initialOffset = "0")
           })
    )*/
    public void transactionListener(Transaction transaction){

        System.out.println(transaction);
    }
}
