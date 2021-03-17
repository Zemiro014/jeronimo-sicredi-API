package com.jeronimo.sicredi_API.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jeronimo.sicredi_API.domain.VotingSession;
import com.jeronimo.sicredi_API.dto.VotingDTO;
import com.jeronimo.sicredi_API.dto.VotingSessionDTO;
import com.jeronimo.sicredi_API.kafka.listener.VotingSessionConsumer;

@RestController
@RequestMapping(value="/votingSessions")
public class VotingSessionResources {
	
	
	private VotingSessionConsumer votingSessionconsumer;
	
	@Autowired
	public VotingSessionResources(VotingSessionConsumer votingSessionconsumer) {
		this.votingSessionconsumer = votingSessionconsumer;
	}

	@GetMapping
	public ResponseEntity<List<VotingSessionDTO>> findAllVotingSession(){
		
		List<VotingSession> list = votingSessionconsumer.findAll();
		List<VotingSessionDTO> list_Dto = list.stream().map(x -> new VotingSessionDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list_Dto);
	}
	

	@RequestMapping(value="/voteGuideline", method=RequestMethod.POST)
	public ResponseEntity<Void> voteGuideline(@RequestBody VotingDTO objDtoFromRequest)
	{
		votingSessionconsumer.voteGuideline(objDtoFromRequest);
		
		return ResponseEntity.noContent().build();
	}

}
