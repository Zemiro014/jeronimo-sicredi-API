package com.jeronimo.sicredi_API.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.domain.Guideline;
import com.jeronimo.sicredi_API.domain.Vote;
import com.jeronimo.sicredi_API.domain.VotingSession;
import com.jeronimo.sicredi_API.domain.enums.VotingValue;
import com.jeronimo.sicredi_API.dto.AuthorDTO;
import com.jeronimo.sicredi_API.dto.VoteForAgendaDTO;
import com.jeronimo.sicredi_API.repositories.AssociateRepository;
import com.jeronimo.sicredi_API.repositories.GuidelineRepository;
import com.jeronimo.sicredi_API.repositories.VoteRepository;
import com.jeronimo.sicredi_API.repositories.VotingSessionRepository;

@Configuration
public class DataBase_instantiater implements CommandLineRunner {

	@Autowired
	private AssociateRepository associateRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private GuidelineRepository guidelineRepository;
	
	@Autowired
	private VotingSessionRepository votingSessionRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		associateRepository.deleteAll();
		voteRepository.deleteAll();
		guidelineRepository.deleteAll();
		votingSessionRepository.deleteAll();
		
		Associate maria = new Associate(null, "Maria Brown", "maria@gmail.com");
		Associate alex = new Associate(null, "Alex Green", "alex@gmail.com");
		Associate bob = new Associate(null, "Bob Grey", "bob@gmail.com");
		
		associateRepository.saveAll(Arrays.asList(maria, alex, bob));			
		
		Guideline guideline1 = new Guideline(null, "Despedir funcionário", "Para se reduzir os custos da empresa");
		Guideline guideline2 = new Guideline(null, "Sala de TI", "Para se aumentar a equipe de TI");
		
		guidelineRepository.saveAll(Arrays.asList(guideline1, guideline2));
		
		Vote vote1 = new Vote(null, VotingValue.NAO, new VoteForAgendaDTO(guideline1), new AuthorDTO(maria));
		Vote vote2 = new Vote(null, VotingValue.SIM, new VoteForAgendaDTO(guideline2), new AuthorDTO(maria));
		Vote vote3 = new Vote(null, VotingValue.SIM, new VoteForAgendaDTO(guideline1), new AuthorDTO(alex));
		Vote vote4 = new Vote(null, VotingValue.NAO, new VoteForAgendaDTO(guideline2), new AuthorDTO(alex));
		Vote vote5 = new Vote(null, VotingValue.NAO, new VoteForAgendaDTO(guideline1), new AuthorDTO(bob));
		Vote vote6 = new Vote(null, VotingValue.SIM, new VoteForAgendaDTO(guideline2), new AuthorDTO(bob));
		
		voteRepository.saveAll(Arrays.asList(vote1, vote2, vote3, vote4, vote5, vote6));
		
		VotingSession session1 = new VotingSession(null, sdf.parse("10/03/2021 09:30:00"), sdf.parse("10/03/2021 11:00:00"), guideline1);
		VotingSession session2 = new VotingSession(null, sdf.parse("10/03/2021 14:00:00"), sdf.parse("10/03/2021 16:00:00"), guideline2);
		session1.getAssociates().addAll(Arrays.asList(maria, alex, bob));
		session2.getAssociates().addAll(Arrays.asList(maria, alex, bob));
		votingSessionRepository.saveAll(Arrays.asList(session1, session2));
		
		maria.getVotes().addAll(Arrays.asList(vote1, vote2));
		alex.getVotes().addAll(Arrays.asList(vote3, vote4));
		bob.getVotes().addAll(Arrays.asList(vote5, vote6));
		associateRepository.save(maria);
		associateRepository.save(alex);
		associateRepository.save(bob);
		
		guideline1.getVotes().addAll(Arrays.asList(vote1, vote3, vote5));
		guideline2.getVotes().addAll(Arrays.asList(vote2, vote4, vote6));		
		guidelineRepository.save(guideline1);
		guidelineRepository.save(guideline2);
	}
}
