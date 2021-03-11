package com.jeronimo.sicredi_API.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.dto.AssociateDTO;
import com.jeronimo.sicredi_API.repositories.AssociateRepository;
import com.jeronimo.sicredi_API.services.eception.ObjectNotFoundException;

@Service
public class AssociateService {
	
	@Autowired
	private AssociateRepository associateRepository;
	
	public List<Associate> findAll()
	{
		return associateRepository.findAll();
	}

	public Associate findById(String id) {
		Optional<Associate> obj = associateRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("O Associado especificado não existe no nosso sistema"));
	}
	
	public Associate insertAssociate(Associate obj) {
		return associateRepository.insert(obj);
	}
	
	public Associate convertAssociateDtoFromAssociate(AssociateDTO obj_Dto) {
		return new Associate(obj_Dto.getId(),obj_Dto.getName(),obj_Dto.getEmail());		
	}
}
