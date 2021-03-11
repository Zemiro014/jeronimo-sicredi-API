package com.jeronimo.sicredi_API.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.repositories.AssociateRepository;

@Configuration
public class DataBase_instantiater implements CommandLineRunner {

	@Autowired
	private AssociateRepository associateRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		associateRepository.deleteAll();
		
		Associate maria = new Associate(null, "Maria Brown", "maria@gmail.com");
		Associate alex = new Associate(null, "Alex Green", "alex@gmail.com");
		Associate bob = new Associate(null, "Bob Grey", "bob@gmail.com");
		
		associateRepository.saveAll(Arrays.asList(maria, alex, bob));
	}
}
