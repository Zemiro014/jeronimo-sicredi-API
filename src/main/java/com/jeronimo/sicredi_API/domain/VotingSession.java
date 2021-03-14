package com.jeronimo.sicredi_API.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VotingSession implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Date dateStart;
	private Date dateFinish;
	private Associate associate;
	private Guideline guideline;
	
	public VotingSession() {
		
	}
	
	public VotingSession(String id, Date dateStart, Date dateFinish, Associate associate, Guideline guideline) {
		super();
		this.id = id;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.associate = associate;
		this.guideline = guideline;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Guideline getGuideline() {
		return guideline;
	}

	public void setGuideline(Guideline guideline) {
		this.guideline = guideline;
	}

	public Date getDateStart() {
		return dateStart;
	}
	
	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VotingSession other = (VotingSession) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
