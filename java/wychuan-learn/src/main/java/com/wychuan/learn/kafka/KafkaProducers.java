package com.wychuan.learn.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducers {

	private static Properties getProperties() {
		Properties props = new Properties();

		props.put("bootstrap.servers", "172.16.162.142:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		return props;
	}

	public static void main(String[] args) {
		Properties properties = getProperties();

		Producer<String, String> producer = new KafkaProducer<>(properties);

		for (int i = 0; i < 100; i++) {
			ProducerRecord<String, String> record = new ProducerRecord<String, String>("test", Integer.toString(i),
					"this is producer record" + i);
			producer.send(record);
		}

		producer.close();
	}

}
