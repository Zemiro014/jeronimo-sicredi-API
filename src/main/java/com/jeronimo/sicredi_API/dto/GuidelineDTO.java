package com.jeronimo.sicredi_API.dto;

import java.io.Serializable;

import com.jeronimo.sicredi_API.domain.Guideline;

public class GuidelineDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String title;
	private String description;
	
	public GuidelineDTO() {
		
	}

	public GuidelineDTO(Guideline obj) {
		this.id = obj.getId();
		this.title = obj.getTitle();
		this.description = obj.getDescription();
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
