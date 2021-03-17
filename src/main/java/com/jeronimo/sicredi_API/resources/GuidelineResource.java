package com.jeronimo.sicredi_API.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.jeronimo.sicredi_API.kafka.listener.GuidelineConsumer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/guidelines")
@Api(value="API REST guideline (subject) -Pautas-")
@CrossOrigin(origins="*")
public class GuidelineResource {

	private GuidelineConsumer guidelineConsumer;
	
	@Autowired
	public GuidelineResource(GuidelineConsumer guidelineConsumer) {
		this.guidelineConsumer = guidelineConsumer;
	}
	
	@GetMapping
	@ApiOperation(value="This method returns all existing guideline (subject) -Pautas- in mongoDB")
	public ResponseEntity<List<GuidelineDTO>> findAllAssociates(){
		
		List<Guideline> list = guidelineConsumer.findAllGuideline();
		List<GuidelineDTO> listDto = list.stream().map(x -> new GuidelineDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ApiOperation(value="This method returns the guideline (subject) -Pautas- that corresponds to the specified Id. "
			+ "It brings with it the voting results of the agenda corresponding to the Id")
	public ResponseEntity<VotingResultDTO> findGuidelineById(@PathVariable String id){
		
		Guideline obj_guideline = guidelineConsumer.findGuidelineById(id);		
		return ResponseEntity.ok().body(new VotingResultDTO(obj_guideline));
	}

	
	@RequestMapping(value="/{id}/votes", method=RequestMethod.GET)
	@ApiOperation(value="This method returns guideline (subject) -Pautas- votes of guideline that corresponds to the specified Id")
	public ResponseEntity<List<String>> getVotesOfGuideline(@PathVariable String id){
		
		Guideline obj_guideline = guidelineConsumer.findGuidelineById(id);
		return ResponseEntity.ok().body(obj_guideline.getVotes());
	}
	
	@PostMapping
	@ApiOperation(value="This method allows inserting a new guideline (subject) -Pautas- in system, "
			+ "inform in your body the values ​​of the fields: title and description")
	public ResponseEntity<Void> insertNewGuideline(@RequestBody GuidelineDTO objDtoFromRequest){
		Guideline obj = guidelineConsumer.converteGuidelineDtoToGuideline(objDtoFromRequest);
		obj = guidelineConsumer.inserNewGuideline(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value="This method allow to delete the guideline (subject) -Pautas- that corresponds to the specified Id")
	public ResponseEntity<Void> deleteGuidelineById(@PathVariable String id){
		guidelineConsumer.deleteGuideline(id);
		return ResponseEntity.noContent().build();
	}
}
