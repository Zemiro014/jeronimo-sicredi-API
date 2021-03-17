package com.jeronimo.sicredi_API.kafka.listener;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.dto.AssociateDTO;
import com.jeronimo.sicredi_API.listener.exception.ObjectNotFoundException;
import com.jeronimo.sicredi_API.repositories.AssociateRepository;

@Service
public class AssociateConsumer {

	private static final Logger logger = LoggerFactory.getLogger(AssociateConsumer.class);
	
	@Autowired
	AssociateRepository associateRepository;

	/*
	@KafkaListener(topics = "Kafka_Example", groupId = "group_id")
	public void consume(String message) {
		logger.info(String.format("Consumed message -> %s",message));
	}
*/
	@KafkaListener(topics = "associate", groupId = "group_json", containerFactory = "associateKafkaListenerFactory")
	public void consumeJson(Associate associate) {
		logger.info(String.format("Consuming new associate in JSON. Data:-> %s",associate));
			insertAssociate(associate);
	}
	
	public List<Associate> findAll()
	{
		return associateRepository.findAll();
	}

	public Associate findById(String id) {
		Optional<Associate> obj = associateRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("O Associado especificado não existe no nosso sistema"));
	}
	
	public Associate insertAssociate(Associate obj) {
		return associateRepository.insert(obj);
	}
	
	public void deleteAssociate(String id) {
		findById(id);
		associateRepository.deleteById(id);
	}
	
	public Associate updateAssociateData(Associate obj_ReceivedFromRequest) {
		Associate obj_toBeUpdated = findById(obj_ReceivedFromRequest.getId());
		updateExistentAssociateDate(obj_toBeUpdated, obj_ReceivedFromRequest);
		return associateRepository.save(obj_toBeUpdated);
	}
	
	private void updateExistentAssociateDate(Associate obj_toBeUpdated, Associate obj_ReceivedFromRequest) {
		obj_toBeUpdated.setName(obj_ReceivedFromRequest.getName());
		obj_toBeUpdated.setEmail(obj_ReceivedFromRequest.getEmail());		
	}
	
	public Associate convertAssociateDtoFromAssociate(AssociateDTO obj_Dto) {
		return new Associate(obj_Dto.getId(),obj_Dto.getName(),obj_Dto.getEmail());		
	}
}
