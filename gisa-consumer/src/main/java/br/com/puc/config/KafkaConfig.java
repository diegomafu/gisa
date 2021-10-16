package br.com.puc.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.puc.entity.Exemplo;

@EnableKafka
@Configuration
public class KafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	
	@Value("${kafka-consumer.max.poll.interval.ms}")
	private String interval;
	
	@Value("${kafka-consumer.session.timeout.ms}")
	private String timeout;
	
	@Bean
	public Map<String, Object> consumerConfig(){
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, interval);
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, timeout);
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
		return props;
	}
	
	@Bean
	public ConsumerFactory<String, Object> consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(consumerConfig());
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Exemplo> kafkaFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Exemplo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(null);
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
		return factory;
	}
}
