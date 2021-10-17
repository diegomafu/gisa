package br.com.puc.consumer;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.puc.entity.Exemplo;
import br.com.puc.repository.ExemploRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ExemploConsumer {

	@Autowired
	private ExemploRepository exemploRepository;
	
	@KafkaListener(topics = "${kafka-consumer.topic}")
	public void consumer(ConsumerRecord<String, String> payload) {
		log.info("Consumer recebendo payload. Value="+payload.value());
		
		Exemplo exemplo = convertMessage(payload.value());
		java.util.Optional<br.com.puc.entity.Exemplo> exemploOld = exemploRepository.findById(exemplo.getId());
		if(exemploOld.isPresent()) {
			Exemplo exemploLast =  exemploRepository.findFirstByOrderByIdDesc();
			exemplo.setId(exemploLast.getId()+1);
		}
		exemploRepository.save(exemplo);
		log.info("Registro processado. Id="+exemplo);
	}

	private Exemplo convertMessage(String item) {
		try {
			
			final String exemploTemp = StringEscapeUtils.unescapeJson(item);
			
			final String exemplo = exemploTemp.substring(1, exemploTemp.length() - 1);
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			return mapper.readValue(exemplo, Exemplo.class);
			
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

}
