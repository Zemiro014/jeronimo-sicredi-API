package com.jeronimo.sicredi_API.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.dto.AssociateDTO;
import com.jeronimo.sicredi_API.kafka.listener.AssociateConsumer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/associates")
@Api(value="API REST Associates")
@CrossOrigin(origins="*")
public class AssociateResource {
	
	private AssociateConsumer associateService;
	
	@Autowired	
	public AssociateResource(AssociateConsumer associateService) {
		this.associateService = associateService;
	}
	
	@GetMapping
	@ApiOperation(value="This method returns all existing associate in mongoDB")
	public ResponseEntity<List<AssociateDTO>> findAllAssociates(){
		
		List<Associate> list = associateService.findAll();
		List<AssociateDTO> listDto = list.stream().map(x -> new AssociateDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ApiOperation(value="This method returns the associate that corresponds to the specified Id")
	public ResponseEntity<AssociateDTO> findAssociateById(@PathVariable String id){
		
		Associate obj_associate = associateService.findById(id);
		
		return ResponseEntity.ok().body(new AssociateDTO(obj_associate));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value="This method allows inserting a new one associated with the system, inform in your body the values ​​of the fields: name and email")
	public ResponseEntity<AssociateDTO> insertAssociateById(@RequestBody AssociateDTO objDtoFromRequest){
		
		Associate obj_associate = associateService.convertAssociateDtoFromAssociate(objDtoFromRequest);
		obj_associate = associateService.insertAssociate(obj_associate);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj_associate.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value="This method allow to delete the associate that corresponds to the specified Id")
	public ResponseEntity<AssociateDTO> deleteAssociate(@PathVariable String id){
		
		associateService.deleteAssociate(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ApiOperation(value="This method allow to update the associate that corresponds to the specified Id. Inform in your body the new values ​​of the fields: name and email")
	public ResponseEntity<AssociateDTO> updateAssociate(@RequestBody AssociateDTO objDtoFromRequest, @PathVariable String id){
		
		Associate obj_associate = associateService.convertAssociateDtoFromAssociate(objDtoFromRequest);
		obj_associate.setId(id);
		obj_associate = associateService.updateAssociateData(obj_associate);
		return ResponseEntity.noContent().build();
	}
}
