package com.jeronimo.sicredi_API.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.domain.Guideline;
import com.jeronimo.sicredi_API.domain.VotingSession;
import com.jeronimo.sicredi_API.dto.VotingSessionDTO;
import com.jeronimo.sicredi_API.repositories.GuidelineRepository;
import com.jeronimo.sicredi_API.repositories.VotingSessionRepository;
import com.jeronimo.sicredi_API.services.eception.VotingNotAllowedException;

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
	
	public VotingSession voteGuideline(VotingSessionDTO obj) {		
		Associate associate = associateService.findById(obj.getAssociateId());
		Guideline guideline = guidelineService.findGuidelineById(obj.getGuidelineId());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try 
		{
			List<VotingSession>	list = findAll();
			for(VotingSession vSession : list)
			{
				if(vSession.getGuideline().getId().equals(guideline.getId())) 
				{
					VotingSession vSession01 = vSession;
					if(vSession01.getAssociate().getId().equals(associate.getId())) 
					{
						throw new VotingNotAllowedException("Não é permitido a um associado votar mais de uma vez em uma mesma pauta !!");
					}
					else 
					{
						VotingSession votingSession = new VotingSession(null,sdf.parse("13/03/2021 16:50:00"),sdf.parse("13/03/2021 16:54:00"), associate, guideline);
						voteSessionRepository.insert(votingSession);
						
						guideline.setVotes(obj.getVote());
						guidelineRepository.save(guideline);				
						
						return votingSession;
					}
				}

			}
			
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}		
		return null;
	}
}
