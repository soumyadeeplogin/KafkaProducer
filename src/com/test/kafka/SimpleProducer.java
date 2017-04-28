package com.test.kafka;

import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {
	public static void main(String[] args) {
		// Check arguments length value

		// Assign topicName to string variable
		// String topicName = args[0].toString();
		String topicName = "test";

		// create instance for properties to access producer configs
		Properties props = new Properties();

		// Assign localhost id
		props.put("bootstrap.servers", "localhost:9092");

		// Set acknowledgements for producer requests.
		props.put("acks", "all");

		// If the request fails, the producer can automatically retry,
		props.put("retries", 0);

		// Specify buffer size in config
		props.put("batch.size", 16384);

		// Reduce the no of requests less than 0
		props.put("linger.ms", 1);

		// The buffer.memory controls the total amount of memory available to
		// the producer for buffering.
		props.put("buffer.memory", 33554432);

		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// Thread.currentThread().setContextClassLoader(null);
		Producer<String, String> producer = new KafkaProducer<String, String>(props);

		/*for (int i = 0; i < 10; i++)
			producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(i), Integer.toString(i)));
		System.out.println("Message sent successfully");
		producer.close();*/
		Scanner sc = new Scanner(new InputStreamReader(System.in));
		String s = "";
		while(true){			
			s = sc.nextLine();
			if(s.equals("exit_kafka")){				
				break;
			} else {
				producer.send(new ProducerRecord<String, String>(topicName, "msg", s));
			}
		}
		producer.send(new ProducerRecord<String, String>(topicName, "msg", s));
		sc.close();
	}
}
