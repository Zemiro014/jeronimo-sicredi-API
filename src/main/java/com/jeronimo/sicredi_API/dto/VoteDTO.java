package com.jeronimo.sicredi_API.dto;

import java.io.Serializable;

import com.jeronimo.sicredi_API.domain.Vote;
import com.jeronimo.sicredi_API.domain.enums.VotingValue;

public class VoteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private VotingValue value;
	private AuthorDTO author;
	
	public VoteDTO(Vote obj) {
		id = obj.getId();
		value = obj.getValue();
		author = obj.getAuthor();
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

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}	
	
}
