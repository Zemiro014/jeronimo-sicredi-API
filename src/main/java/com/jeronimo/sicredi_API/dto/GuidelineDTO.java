package com.jeronimo.sicredi_API.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.jeronimo.sicredi_API.domain.Guideline;
import com.jeronimo.sicredi_API.domain.Vote;

public class GuidelineDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String title;
	private String description;
	
	private List<VoteDTO> votes;
	
	public GuidelineDTO() {
		
	}

	public GuidelineDTO(Guideline obj) {
		this.id = obj.getId();
		this.title = obj.getTitle();
		this.description = obj.getDescription();
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

	public List<VoteDTO> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes.stream().map(x -> new VoteDTO(x)).collect(Collectors.toList());
	}	
	
}
