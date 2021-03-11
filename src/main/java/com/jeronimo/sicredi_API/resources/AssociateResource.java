package com.jeronimo.sicredi_API.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.dto.AssociateDTO;
import com.jeronimo.sicredi_API.services.AssociateService;

@RestController
@RequestMapping(value="/associates")
public class AssociateResource {

	@Autowired
	private AssociateService associateService;
	
	@GetMapping
	public ResponseEntity<List<AssociateDTO>> findAllAssociates(){
		
		List<Associate> list = associateService.findAll();
		List<AssociateDTO> listDto = list.stream().map(x -> new AssociateDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<AssociateDTO> findAssociateById(@RequestBody AssociateDTO objDtoFromRequest){
		
		Associate obj_associate = associateService.convertAssociateDtoFromAssociate(objDtoFromRequest);
		obj_associate = associateService.insertAssociate(obj_associate);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj_associate.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AssociateDTO> insertAssociateById(@PathVariable String id){
		
		Associate obj_associate = associateService.findById(id);
		
		return ResponseEntity.ok().body(new AssociateDTO(obj_associate));
	}
}
