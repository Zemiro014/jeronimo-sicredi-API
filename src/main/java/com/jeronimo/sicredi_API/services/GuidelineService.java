package com.jeronimo.sicredi_API.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeronimo.sicredi_API.domain.Guideline;
import com.jeronimo.sicredi_API.dto.GuidelineDTO;
import com.jeronimo.sicredi_API.repositories.GuidelineRepository;
import com.jeronimo.sicredi_API.services.exception.ObjectNotFoundException;

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
	
	public Guideline inserNewGuideline(Guideline obj) {
		return guidelineRepository.insert(obj);
	}	
	
	public void deleteGuideline(String id) {
		findGuidelineById(id);
		guidelineRepository.deleteById(id);
	}
	
	public Guideline updateGuidelineData(Guideline obj_ReceivedFromRequest) {
		Guideline obj_toBeUpdated = findGuidelineById(obj_ReceivedFromRequest.getId());
		updateExistentGuidelineDate(obj_toBeUpdated, obj_ReceivedFromRequest);
		return guidelineRepository.save(obj_toBeUpdated);
	}
	
	private void updateExistentGuidelineDate(Guideline obj_toBeUpdated, Guideline obj_ReceivedFromRequest) {
		obj_toBeUpdated.setTitle(obj_ReceivedFromRequest.getTitle());
		obj_toBeUpdated.setDescription(obj_ReceivedFromRequest.getDescription());
	}
	
	public Guideline converteGuidelineDtoToGuideline(GuidelineDTO obj) {
		return new Guideline(obj.getId(), obj.getTitle(), obj.getDescription());
	}
	
}
