package com.jeronimo.sicredi_API.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.services.AssociateService;

@RestController
@RequestMapping(value="/associates")
public class AssociateResource {

	@Autowired
	private AssociateService associateService;
	
	@GetMapping
	public ResponseEntity<List<Associate>> findAllAssociates(){
		
		List<Associate> list = associateService.findAll();
		
		return ResponseEntity.ok().body(list);
	}
}
