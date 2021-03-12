package com.jeronimo.sicredi_API.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.domain.Guideline;
import com.jeronimo.sicredi_API.domain.Vote;
import com.jeronimo.sicredi_API.domain.enums.VotingValue;
import com.jeronimo.sicredi_API.dto.AuthorDTO;
import com.jeronimo.sicredi_API.dto.VoteForAgendaDTO;
import com.jeronimo.sicredi_API.repositories.AssociateRepository;
import com.jeronimo.sicredi_API.repositories.GuidelineRepository;
import com.jeronimo.sicredi_API.repositories.VoteRepository;

@Configuration
public class DataBase_instantiater implements CommandLineRunner {

	@Autowired
	private AssociateRepository associateRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private GuidelineRepository guidelineRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		associateRepository.deleteAll();
		voteRepository.deleteAll();
		
		Associate maria = new Associate(null, "Maria Brown", "maria@gmail.com");
		Associate alex = new Associate(null, "Alex Green", "alex@gmail.com");
		Associate bob = new Associate(null, "Bob Grey", "bob@gmail.com");
		
		associateRepository.saveAll(Arrays.asList(maria, alex, bob));			
		
		Guideline guideline1 = new Guideline(null, "Despedir funcionário", "Para se reduzir os custos da empresa");
		Guideline guideline2 = new Guideline(null, "Sala de TI", "Para se aumentar a equipe de TI");
		
		guidelineRepository.saveAll(Arrays.asList(guideline1, guideline2));
		
		Vote vote1 = new Vote(null, VotingValue.NAO, new VoteForAgendaDTO(guideline1), new AuthorDTO(maria));
		Vote vote2 = new Vote(null, VotingValue.SIM, new VoteForAgendaDTO(guideline2), new AuthorDTO(maria));	
		
		voteRepository.saveAll(Arrays.asList(vote1, vote2));
		maria.getVotes().addAll(Arrays.asList(vote1, vote2));
		associateRepository.save(maria);
		
		guideline1.getVotes().add(vote1);
		guideline2.getVotes().add(vote2);		
		guidelineRepository.saveAll(Arrays.asList(guideline1, guideline2));
	}
}
