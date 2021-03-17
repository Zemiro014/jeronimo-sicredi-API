package com.jeronimo.sicredi_API.kafka.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.jeronimo.sicredi_API.domain.VotingSession;

@EnableKafka
@Configuration
public class KafkaVotingSessionConfig {

	@Bean 
	public ConsumerFactory<String, VotingSession> votingSessionConsumerFactory(){
		return new DefaultKafkaConsumerFactory<>(KafkaConfig.configuration(), new StringDeserializer(), new JsonDeserializer<>(VotingSession.class));
	}
	
	@Bean 
	public ConcurrentKafkaListenerContainerFactory<String, VotingSession> votingSessionKafkaListenerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, VotingSession> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(votingSessionConsumerFactory());
        return factory;
	}	
}
