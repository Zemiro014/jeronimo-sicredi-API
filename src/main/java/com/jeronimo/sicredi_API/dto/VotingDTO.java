package com.jeronimo.sicredi_API.dto;

import java.io.Serializable;

public class VotingDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String associateId;
	private String guidelineId;
	private String vote;
	
	public VotingDTO() {
		
	}

	public VotingDTO(String id, String associateId,	String guidelineId, String vote) {
		this.id = id;
		this.associateId = associateId;
		this.guidelineId = guidelineId;
		this.vote = vote;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssociateId() {
		return associateId;
	}

	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}

	public String getGuidelineId() {
		return guidelineId;
	}

	public void setGuidelineId(String guidelineId) {
		this.guidelineId = guidelineId;
	}

	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return "VotingDTO [id=" + id + ", associateId=" + associateId + ", guidelineId=" + guidelineId + ", vote="
				+ vote + "]";
	}	
	
	
	
}
