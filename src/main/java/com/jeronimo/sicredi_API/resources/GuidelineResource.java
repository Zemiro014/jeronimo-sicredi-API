package com.jeronimo.sicredi_API.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeronimo.sicredi_API.domain.Guideline;
import com.jeronimo.sicredi_API.dto.GuidelineDTO;
import com.jeronimo.sicredi_API.dto.VotingResultDTO;
import com.jeronimo.sicredi_API.services.GuidelineService;

@RestController
@RequestMapping(value="/guidelines")
public class GuidelineResource {

	@Autowired
	private GuidelineService guidelineService;
	
	@GetMapping
	public ResponseEntity<List<GuidelineDTO>> findAllAssociates(){
		
		List<Guideline> list = guidelineService.findAllGuideline();
		List<GuidelineDTO> listDto = list.stream().map(x -> new GuidelineDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<VotingResultDTO> findGuidelineById(@PathVariable String id){
		
		Guideline obj_guideline = guidelineService.findGuidelineById(id);		
		return ResponseEntity.ok().body(new VotingResultDTO(obj_guideline));
	}

	
	@RequestMapping(value="/{id}/votes", method=RequestMethod.GET)
	public ResponseEntity<List<String>> getVotesOfGuideline(@PathVariable String id){
		
		Guideline obj_guideline = guidelineService.findGuidelineById(id);
		return ResponseEntity.ok().body(obj_guideline.getVotes());
	}
	
	@PostMapping
	public ResponseEntity<Void> insertNewGuideline(@RequestBody GuidelineDTO objDtoFromRequest){
		Guideline obj = guidelineService.converteGuidelineDtoToGuideline(objDtoFromRequest);
		obj = guidelineService.inserNewGuideline(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteGuidelineById(@PathVariable String id){
		guidelineService.deleteGuideline(id);
		return ResponseEntity.noContent().build();
	}
}
