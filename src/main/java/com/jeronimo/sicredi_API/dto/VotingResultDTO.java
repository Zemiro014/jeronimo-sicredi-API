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
	private int approved;
	private int rejected;
	private String status;
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
	
	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public int getRejected() {
		return rejected;
	}

	public void setRejected(int rejected) {
		this.rejected = rejected;
	}


	public List<String> getVotes() {
		return votes;
	}

	public void setVotes(List<String> votes) {
		this.votes = votes;
		calculatingStateVoting(this.votes);
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String state) {
		this.status = state;
	}
	
	private void calculatingStateVoting(List<String> votes) {
		int sim = 0;
		int nao = 0;
		
		if(votes == null) 
		{
			setStatus("Esta pauta não possui nenhuma votação");
		}
		else 
		{
			for(String vote : votes) 
			{
				if(vote.equals("SIM")) 
				{
					sim += 1;
				}
				else 
				{
					nao += 1;
				}
			}
			if(sim > nao) {
				setStatus("APROVADA");
			}
			else if(sim == nao)
			{
				setStatus("EMPATADA");
			}
			else {
				setStatus("REPROVADA");
			}
		}
		setApproved(sim);
		setRejected(nao);
	}
}
