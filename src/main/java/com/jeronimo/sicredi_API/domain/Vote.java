package com.jeronimo.sicredi_API.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jeronimo.sicredi_API.domain.enums.VotingValue;

@Document
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private VotingValue value;
	private Associate associate;
	
	private List<Guideline> guidelines = new ArrayList<>();
	
	public Vote() {
		
	}

	public Vote(String id, VotingValue value, Associate associate) {
		this.id = id;
		this.value = value;
		this.associate = associate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public VotingValue getValue() {
		return value;
	}

	public void setValue(VotingValue value) {
		this.value = value;
	}

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}
	
	public List<Guideline> getGuidelines() {
		return guidelines;
	}

	public void aGuidelines(List<Guideline> guidelines) {
		this.guidelines = guidelines;
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
		Vote other = (Vote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
