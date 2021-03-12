package com.jeronimo.sicredi_API.dto;

import java.io.Serializable;

import com.jeronimo.sicredi_API.domain.Guideline;

public class VoteForAgendaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;
	private String description;
	
	public VoteForAgendaDTO() {
		
	}

	public VoteForAgendaDTO(Guideline obj) {
		id = obj.getId();
		title = obj.getTitle();
		description = obj.getDescription();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
}
