package com.mustunal.usertrackingproducer;

import com.mustunal.usertrackingproducer.model.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class UserTrackingProducerApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(UserTrackingProducerApplication.class, args);

		EventGenerator eventGenerator = new EventGenerator();

		Properties properties = new Properties();
		properties.put("bootstrap.servers","localhost:9093,localhost:9094");
		properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

		Producer<String,String> producer = new KafkaProducer<String, String>(properties);

		for (int i = 0; i < 10; i++) {
			Event event = eventGenerator.generateEvent();

			String key = extractKey(event);
			String value = extractValue(event);

			ProducerRecord<String,String> producerRecord = new ProducerRecord<>("user-tracking",key,value);

			producer.send(producerRecord);

			System.out.println("Message Sent");

			Thread.sleep(1000);
		}

		producer.close();

	}

	private static String extractKey(Event event){
		return event.getUser().getUserId().toString();
	}

	private static String extractValue(Event event){
		return String.format("%s,%s,%s",event.getProduct().getProductType(),event.getProduct().getDesignType(),event.getProduct().getColor());
	}

}
