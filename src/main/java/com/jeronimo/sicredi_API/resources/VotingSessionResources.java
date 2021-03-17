package com.jeronimo.sicredi_API.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jeronimo.sicredi_API.domain.VotingSession;
import com.jeronimo.sicredi_API.dto.VotingDTO;
import com.jeronimo.sicredi_API.dto.VotingSessionDTO;
import com.jeronimo.sicredi_API.kafka.listener.VotingSessionConsumer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/votingSessions")
@Api(value="API REST Voting Session")
@CrossOrigin(origins="*")
public class VotingSessionResources {
	
	
	private VotingSessionConsumer votingSessionconsumer;
	
	@Autowired
	public VotingSessionResources(VotingSessionConsumer votingSessionconsumer) {
		this.votingSessionconsumer = votingSessionconsumer;
	}

	@GetMapping
	@ApiOperation(value="This method returns all existing Voting Session in mongoDB")
	public ResponseEntity<List<VotingSessionDTO>> findAllVotingSession(){
		
		List<VotingSession> list = votingSessionconsumer.findAll();
		List<VotingSessionDTO> list_Dto = list.stream().map(x -> new VotingSessionDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list_Dto);
	}
	

	@RequestMapping(value="/voteGuideline", method=RequestMethod.POST)
	@ApiOperation(value="This method allows you to vote on a specific agenda registered in the system. "
			+ "It is necessary to inform in your body the values ​​of the fields: guidelineId; associateId and vote. "
			+ " The guidelineId must match a valid guideline. "
			+ " The associateId must correspond to a registered associate and the vote must be only SIM or NAO")
	public ResponseEntity<Void> voteGuideline(@RequestBody VotingDTO objDtoFromRequest)
	{
		votingSessionconsumer.voteGuideline(objDtoFromRequest);
		
		return ResponseEntity.noContent().build();
	}

}
