package com.jeronimo.sicredi_API.kafka.listener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.domain.Guideline;
import com.jeronimo.sicredi_API.domain.VotingSession;
import com.jeronimo.sicredi_API.dto.VotingDTO;
import com.jeronimo.sicredi_API.listener.exception.ObjectNullException;
import com.jeronimo.sicredi_API.listener.exception.VotingNotAllowedException;
import com.jeronimo.sicredi_API.repositories.GuidelineRepository;
import com.jeronimo.sicredi_API.repositories.VotingSessionRepository;

@Service
public class VotingSessionConsumer {

	private static final Logger logger = LoggerFactory.getLogger(VotingSessionConsumer.class);
	
	@Autowired
	private VotingSessionRepository voteSessionRepository;
	
	@Autowired
	private GuidelineRepository guidelineRepository;
	
	@Autowired
	private GuidelineConsumer guidelineConsumer;
	
	@Autowired
	private AssociateConsumer associateConsumer;

	/*
	@KafkaListener(topics = "Kafka_Example", groupId = "group_id")
	public void consume(String message) {
		logger.info(String.format("Consumed message -> %s",message));
	}
*/
	@KafkaListener(topics = "votingSession", groupId = "group_json", containerFactory = "votingSessionKafkaListenerFactory")
	public void consumeJson(VotingSession votingSession) {
		logger.info(String.format("Consumed JSON message -> %s",votingSession));
		inserNewVotingSession(votingSession);
	}
	
	public void inserNewVotingSession(VotingSession obj) {
		voteSessionRepository.insert(obj);
	}
	
	public List<VotingSession> findAll() {		
		return voteSessionRepository.findAll();
	}
	
	public VotingSession voteGuideline(VotingDTO obj) {
		if(obj==null)
			throw new ObjectNullException("Ação de votar uma pauta foi negada porque o objecto passado é null");
		
		Associate associate = associateConsumer.findById(obj.getAssociateId());
		Guideline guideline = guidelineConsumer.findGuidelineById(obj.getGuidelineId());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try 
		{	
			VotingSession resul = voteSessionRepository.searchExistentVote(guideline.getId(), associate.getId());
			
			if(resul == null) 
			{				
				if(obj.getVote().equals("SIM") || obj.getVote().equals("NAO")) 
				{
					VotingSession votingSession = new VotingSession(null,sdf.parse("13/03/2021 16:50:00"),sdf.parse("13/03/2021 16:54:00"), associate, guideline);
					voteSessionRepository.insert(votingSession);
					
					guideline.setVotes(obj.getVote());
					guidelineRepository.save(guideline);		
					
					return votingSession;
				}
				else 
				{
					throw new VotingNotAllowedException("O valor do voto não é permitido. Escreva SIM ou NAO !!");
				}				
			}
			else 
			{
				throw new VotingNotAllowedException("Não é permitido a um associado votar mais de uma vez em uma mesma pauta !!");
			}
			
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}		
		return null;
	}
	
}
