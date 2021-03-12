package com.jeronimo.sicredi_API.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jeronimo.sicredi_API.domain.enums.VotingValue;
import com.jeronimo.sicredi_API.dto.AuthorDTO;
import com.jeronimo.sicredi_API.dto.VoteForAgendaDTO;

@Document
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private VotingValue value;
	private VoteForAgendaDTO forAgenda;
	private AuthorDTO author;	
	
	public Vote() {
		
	}

	public Vote(String id, VotingValue value, VoteForAgendaDTO forAgenda, AuthorDTO author) {
		this.id = id;
		this.value = value;
		this.forAgenda = forAgenda;
		this.author = author;		
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
	
	public VoteForAgendaDTO getForAgenda() {
		return forAgenda;
	}

	public void setForAgenda(VoteForAgendaDTO guideline) {
		this.forAgenda = guideline;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
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
