package com.jeronimo.sicredi_API.kafka.listener;

import java.util.List;
import java.util.Optional;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.jeronimo.sicredi_API.domain.Guideline;
import com.jeronimo.sicredi_API.dto.GuidelineDTO;
import com.jeronimo.sicredi_API.listener.exception.ObjectNotFoundException;
import com.jeronimo.sicredi_API.listener.exception.ObjectNullException;
import com.jeronimo.sicredi_API.repositories.GuidelineRepository;

@Service
public class GuidelineConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	@Autowired
	GuidelineRepository guidelineRepository;
/*
	@KafkaListener(topics = "Kafka_Example", groupId = "group_id")
	public void consume(String message) {
		logger.info(String.format("Consumed message -> %s",message));
	}
*/
	@KafkaListener(topics = "guideline", groupId = "group_json", containerFactory = "guidelineKafkaListenerFactory")
	public void consumeJson(GuidelineDTO guidelineDto) {
		logger.info(String.format("Consumed JSON message -> %s",guidelineDto));
		inserNewGuideline(converteGuidelineDtoToGuideline(guidelineDto));
	}
	
	public List<Guideline> findAllGuideline(){
		return guidelineRepository.findAll();
	}
	
	public Guideline findGuidelineById(String id) {
		Optional<Guideline> obj = guidelineRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("A pauta especificada não existe no nosso sistema"));
	}
	
	public Guideline inserNewGuideline(Guideline obj) {
		if(obj==null)
			throw new ObjectNullException("Ação de inserir nova pauta foi negada porque o objecto passado é null");
		return guidelineRepository.insert(obj);
	}	
	
	public void deleteGuideline(String id) {
		findGuidelineById(id);
		guidelineRepository.deleteById(id);
	}
	
	public Guideline updateGuidelineData(Guideline obj_ReceivedFromRequest) {
		Guideline obj_toBeUpdated = findGuidelineById(obj_ReceivedFromRequest.getId());
		updateExistentGuidelineDate(obj_toBeUpdated, obj_ReceivedFromRequest);
		return guidelineRepository.save(obj_toBeUpdated);
	}
	
	private void updateExistentGuidelineDate(Guideline obj_toBeUpdated, Guideline obj_ReceivedFromRequest) {
		obj_toBeUpdated.setTitle(obj_ReceivedFromRequest.getTitle());
		obj_toBeUpdated.setDescription(obj_ReceivedFromRequest.getDescription());
	}
	
	public Guideline converteGuidelineDtoToGuideline(GuidelineDTO obj) {
		return new Guideline(obj.getId(), obj.getTitle(), obj.getDescription());
	}
}
