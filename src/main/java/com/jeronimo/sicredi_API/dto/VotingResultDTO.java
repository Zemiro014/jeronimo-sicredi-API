package com.jeronimo.sicredi_API.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.jeronimo.sicredi_API.domain.Guideline;

public class VotingResultDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;
	private String description;	
	private int quantityVotes;
	private List<String> votes = new ArrayList<>();
	
	public VotingResultDTO(Guideline obj) {
		id = obj.getId();
		title = obj.getTitle();
		description = obj.getDescription();
		quantityVotes = obj.getVotes().size();
		setVotes(obj.getVotes());
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

	public int getQuantityVotes() {
		return quantityVotes;
	}

	public void setQuantityVotes(int quantityVotes) {
		this.quantityVotes = quantityVotes;
	}

	public List<String> getVotes() {
		return votes;
	}

	public void setVotes(List<String> votes) {
		this.votes = votes;
	}	

}
