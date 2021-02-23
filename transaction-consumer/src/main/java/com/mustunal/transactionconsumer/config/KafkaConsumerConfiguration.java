package com.mustunal.transactionconsumer.config;

import com.mustunal.model.Transaction;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfiguration {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootStrapServers;

    @Value("${group.id}")
    private String groupId;

    @Bean
    public Map<String,Object> consumerConfig(){
        Map<String,Object> configMap = new HashMap<>();

        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        return configMap;
    }

    @Bean
    public ConsumerFactory<String, Transaction> consumerFactory(){
        JsonDeserializer<Transaction> deserializer = new JsonDeserializer<>(Transaction.class);
        deserializer.addTrustedPackages("com.mustunal.model.*");

        return new DefaultKafkaConsumerFactory<>(consumerConfig(),
                new StringDeserializer(),
                deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Transaction> listenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Transaction> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        listenerContainerFactory.setConsumerFactory(consumerFactory());

        return listenerContainerFactory;
    }
}
