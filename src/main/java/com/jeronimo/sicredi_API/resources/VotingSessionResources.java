package com.jeronimo.sicredi_API.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeronimo.sicredi_API.domain.VotingSession;
import com.jeronimo.sicredi_API.dto.VotingSessionDTO;
import com.jeronimo.sicredi_API.services.VotingSessionService;

@RestController
@RequestMapping(value="/votingSession")
public class VotingSessionResources {
	
	@Autowired
	private VotingSessionService votingSessionService;

	@GetMapping
	public ResponseEntity<List<VotingSession>> findAllAssociates(){
		
		List<VotingSession> list = votingSessionService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}/votes", method=RequestMethod.POST)
	public ResponseEntity<Void> voteGuideline(@RequestBody VotingSessionDTO objDtoFromRequest, @PathVariable String id)
	{	
		objDtoFromRequest.setGuidelineId(id);
		VotingSession obj_votingSession = votingSessionService.voteGuideline(objDtoFromRequest);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj_votingSession.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
