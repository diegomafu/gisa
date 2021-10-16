package br.com.puc.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.puc.entity.Exemplo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Producer {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	@Value("${kafka-producer.topic}")
	private String topic;
	
	public void send(Exemplo exemplo) throws JsonProcessingException {
		log.info("Enviando Exemplo. Id="+exemplo.getId());
		this.kafkaTemplate.send(topic, new ObjectMapper().writeValueAsString(exemplo));
	}
	
}
