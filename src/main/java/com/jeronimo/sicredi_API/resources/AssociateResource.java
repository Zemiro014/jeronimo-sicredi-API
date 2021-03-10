package com.jeronimo.sicredi_API.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeronimo.sicredi_API.domain.Associate;

@RestController
@RequestMapping(value="/associates")
public class AssociateResource {

	@GetMapping
	public ResponseEntity<List<Associate>> findAll(){
		Associate maria = new Associate("1", "Maria Brown", "maria@gmail.com");
		Associate alex = new Associate("2", "Alex Green", "alex@gmail.com");
		Associate bob = new Associate("3", "Bob Marlay", "bob@gmail.com");
		
		List<Associate> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, alex, bob));
		
		return ResponseEntity.ok().body(list);
	}
}
