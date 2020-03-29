package com.learning.javakafka.kafkajava.configuration;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfigurations {

    private static final String KAFKA_BROKER = "localhost:9092";
    private static final String GROUP_ID = "kafka-sandbox";

    
    @Bean
    public Map<String, Object> consumerConfiguration(){
    	Map<String, Object> configuration = new HashMap<>();
    	configuration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
    	configuration.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
    	configuration.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    	configuration.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    	return configuration;
    }
    @Bean 
    public ConsumerFactory<String, String> consumerFacatory(){
    	return new DefaultKafkaConsumerFactory<String, String>(consumerConfiguration());
    }
    
    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
    	ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
    	factory.setConsumerFactory(consumerFacatory());
    	return factory;
    }
}