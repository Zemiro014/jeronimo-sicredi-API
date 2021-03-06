package com.jeronimo.sicredi_API;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.repositories.AssociateRepository;


@DataMongoTest
@SpringBootTest
@TestPropertySource(locations = "/application-integrationtest.properties")
class SicrediApiApplicationTests {

	@Autowired
	private AssociateRepository associateRepository;
	
	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void createShouldPersistData() {
		Associate associateExemp1 = new Associate(null, "Associado Exemplo 1", "associado1@gmail.com");
		this.associateRepository.save(associateExemp1);
		Assertions.assertThat(associateExemp1.getId()).isNotNull();
		Assertions.assertThat(associateExemp1.getName()).isEqualTo("Associado Exemplo 1");
		Assertions.assertThat(associateExemp1.getEmail()).isEqualTo("associado1@gmail.com");
	}
	
	@Test
	void contextLoads() {
	}	

}
