package com.jeronimo.sicredi_API.kafka.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.jeronimo.sicredi_API.domain.Associate;

@EnableKafka
@Configuration
public class KafkaAssociateConfig {
	
    @Bean
    public ConsumerFactory<String, Associate> associateConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(KafkaConfig.configuration(), new StringDeserializer(), new JsonDeserializer<>(Associate.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Associate> associateKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Associate> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(associateConsumerFactory());
        return factory; 
    }
}
