package com.learning.javakafka.kafkajava.configuration;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfigurations {

    private static final String KAFKA_BROKER = "localhost:9092";

    @Bean
   public ProducerFactory<String, String> producerFactory(){
    	return new DefaultKafkaProducerFactory<>(producerConfiguration());
    }
    @Bean
    public Map<String, Object> producerConfiguration(){
    	Map<String, Object> configuration = new HashMap<>();
    	configuration.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
    	configuration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    	configuration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    	return configuration;
    }
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(){
    	return new KafkaTemplate<>(producerFactory());
    }
}
