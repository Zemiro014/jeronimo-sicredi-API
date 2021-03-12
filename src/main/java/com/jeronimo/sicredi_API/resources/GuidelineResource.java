package com.jeronimo.sicredi_API.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jeronimo.sicredi_API.domain.Guideline;
import com.jeronimo.sicredi_API.dto.GuidelineDTO;
import com.jeronimo.sicredi_API.dto.VoteDTO;
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
	public ResponseEntity<GuidelineDTO> findGuidelineById(@PathVariable String id){
		
		Guideline obj_guideline = guidelineService.findGuidelineById(id);		
		return ResponseEntity.ok().body(new GuidelineDTO(obj_guideline));
	}
		
	@RequestMapping(value="/{id}/votes", method=RequestMethod.GET)
	public ResponseEntity<List<VoteDTO>> findVotesOfAssociate(@PathVariable String id){
		
		Guideline obj_guideline = guidelineService.findGuidelineById(id);
		return ResponseEntity.ok().body(guidelineService.converteGuidelineToGuidelineDTO(obj_guideline).getVotes());
	}
}
