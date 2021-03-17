package com.jeronimo.sicredi_API.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
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
    public ConsumerFactory<String, String> consumerFactoryToGuidelineDTO() {
        Map<String, Object> config = new HashMap();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> guidelineKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactoryToGuidelineDTO());
        return factory; 
    }

    @Bean
    public ConsumerFactory<String, GuidelineDTO> guidelineDTOConsumerFactory() {
        Map<String, Object> config = new HashMap();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(GuidelineDTO.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, GuidelineDTO> guidelineKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, GuidelineDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(guidelineDTOConsumerFactory());
        return factory; 
    }

}
