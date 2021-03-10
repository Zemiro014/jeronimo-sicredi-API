package com.jeronimo.sicredi_API.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.repositories.AssociateRepository;

@Service
public class AssociateService {
	
	@Autowired
	private AssociateRepository associateRepository;
	
	public List<Associate> findAll()
	{
		return associateRepository.findAll();
	}

}
