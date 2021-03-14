package com.jeronimo.sicredi_API.dto;

import java.io.Serializable;
import java.util.Date;

public class VotingSessionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private Date dateStart;
	private Date dateFinish;
	private String associateId;
	private String guidelineId;
	private String vote;
	
	public VotingSessionDTO() {
		
	}

	public VotingSessionDTO(String id, String associateId,	String guidelineId, String vote) {
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

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
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
	
	
}
