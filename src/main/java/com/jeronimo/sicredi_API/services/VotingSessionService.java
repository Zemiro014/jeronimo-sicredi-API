package com.jeronimo.sicredi_API.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.domain.Guideline;
import com.jeronimo.sicredi_API.domain.VotingSession;
import com.jeronimo.sicredi_API.dto.VotingDTO;
import com.jeronimo.sicredi_API.repositories.GuidelineRepository;
import com.jeronimo.sicredi_API.repositories.VotingSessionRepository;
import com.jeronimo.sicredi_API.services.exception.VotingNotAllowedException;

@Service
public class VotingSessionService {

	@Autowired
	private VotingSessionRepository voteSessionRepository;
	
	@Autowired
	private GuidelineRepository guidelineRepository;
	
	@Autowired
	private GuidelineService guidelineService;
	
	@Autowired
	private AssociateService associateService;
	
	public List<VotingSession> findAll() {		
		return voteSessionRepository.findAll();
	}
	
	public VotingSession voteGuideline(VotingDTO obj) {		
		Associate associate = associateService.findById(obj.getAssociateId());
		Guideline guideline = guidelineService.findGuidelineById(obj.getGuidelineId());
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
