package com.jeronimo.sicredi_API.kafka.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.jeronimo.sicredi_API.dto.GuidelineDTO;

@EnableKafka
@Configuration
public class KafkaGuidelineConfig {
	
	@Bean
	public ConsumerFactory<String, GuidelineDTO> guidelineConsumerFactory(){
        return new DefaultKafkaConsumerFactory<>(KafkaConfig.configuration(), new StringDeserializer(), new JsonDeserializer<>(GuidelineDTO.class));
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, GuidelineDTO> guidelineKafkaListenerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, GuidelineDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(guidelineConsumerFactory());
        return factory;
	}
}
