package com.mustunal.usertrackingconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class UserTrackingConsumerApplication {

	@Value("${kafka.servers}")
	private String kafkaServers;
	private static String kafkaServersStatic;

	@Value("${kafka.servers}")
	public void setNameStatic(String kafkaServers){
		UserTrackingConsumerApplication.kafkaServersStatic = kafkaServers;
	}

	@Value("${kafka.consumers.groupId}")
	private String consumerGroupId;

	public static void main(String[] args) {
		SpringApplication.run(UserTrackingConsumerApplication.class, args);

		Properties properties = new Properties();
		properties.put("bootstrap.servers",kafkaServersStatic);
		properties.put("group.id","user-tracking-consumer");
		properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);

		List<String> topicList = new ArrayList<>();
		topicList.add("user-tracking");

		consumer.subscribe(topicList);

		while(true) {
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(100));

			for (ConsumerRecord<String, String> record : consumerRecords) {
				System.out.println(String.format("Key, Value:%s, %s", record.key(), record.value()));
			}
		}
	}

}
