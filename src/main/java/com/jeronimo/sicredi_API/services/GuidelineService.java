package com.jeronimo.sicredi_API.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeronimo.sicredi_API.domain.Guideline;
import com.jeronimo.sicredi_API.dto.GuidelineDTO;
import com.jeronimo.sicredi_API.repositories.GuidelineRepository;
import com.jeronimo.sicredi_API.services.eception.ObjectNotFoundException;

@Service
public class GuidelineService {

	@Autowired
	private GuidelineRepository guidelineRepository;
	
	public List<Guideline> findAllGuideline(){
		return guidelineRepository.findAll();
	}
	
	public Guideline findGuidelineById(String id) {
		Optional<Guideline> obj = guidelineRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("A pauta especificada não existe no nosso sistema"));
	}
	
	public GuidelineDTO converteGuidelineToGuidelineDTO(Guideline obj) {
		return new GuidelineDTO(obj);
	}
}
