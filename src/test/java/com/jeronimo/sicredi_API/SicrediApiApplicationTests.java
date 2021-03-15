package com.jeronimo.sicredi_API;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.services.AssociateService;

@SpringBootTest
@AutoConfigureMockMvc
class SicrediApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AssociateService service;
	
	List<Associate> list = new ArrayList<>();
	
	@Test
	public void associateService() throws Exception {
		when(service.findAll()).thenReturn(list);
		this.mockMvc.perform(get("/associates")).andDo(print()).andExpect(status().isOk());
				//.andExpect(content().string(containsString("Hello, Mock")));
	}
	
	@Test
	void contextLoads() {
	}
	

}
