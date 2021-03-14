package com.jeronimo.sicredi_API.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.domain.Guideline;
import com.jeronimo.sicredi_API.domain.VotingSession;
import com.jeronimo.sicredi_API.domain.enums.VotingValue;
import com.jeronimo.sicredi_API.repositories.AssociateRepository;
import com.jeronimo.sicredi_API.repositories.GuidelineRepository;
import com.jeronimo.sicredi_API.repositories.VotingSessionRepository;

@Configuration
public class DataBase_instantiater implements CommandLineRunner {

	@Autowired
	private AssociateRepository associateRepository;	
	
	@Autowired
	private GuidelineRepository guidelineRepository;
	
	@Autowired
	private VotingSessionRepository votingSessionRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		associateRepository.deleteAll();
		guidelineRepository.deleteAll();
		votingSessionRepository.deleteAll();
		
		Associate maria = new Associate(null, "Maria Brown", "maria@gmail.com");
		Associate alex = new Associate(null, "Alex Green", "alex@gmail.com");
		Associate bob = new Associate(null, "Bob Grey", "bob@gmail.com");
		
		associateRepository.saveAll(Arrays.asList(maria, alex, bob));			
		
		Guideline guideline1 = new Guideline(null, "Despedir funcionário", "Para se reduzir os custos da empresa");
		Guideline guideline2 = new Guideline(null, "Sala de TI", "Para se aumentar a equipe de TI");
		
		guidelineRepository.saveAll(Arrays.asList(guideline1, guideline2));		

		
		VotingSession session1 = new VotingSession(null,sdf.parse("13/03/2021 14:19:00"), sdf.parse("13/03/2021 14:21:00"), maria, guideline1);
		VotingSession session2 = new VotingSession(null, sdf.parse("13/03/2021 14:22:00"),sdf.parse("13/03/2021 14:24:00"), alex, guideline1);
		votingSessionRepository.saveAll(Arrays.asList(session1, session2));
		
		
			guideline1.setVotes(VotingValue.SIM.toString());
			guideline1.setVotes(VotingValue.SIM.toString());
			guideline2.setVotes(VotingValue.NAO.toString());
			guideline2.setVotes(VotingValue.SIM.toString());
			guidelineRepository.save(guideline1);
			guidelineRepository.save(guideline2);
	}
}
