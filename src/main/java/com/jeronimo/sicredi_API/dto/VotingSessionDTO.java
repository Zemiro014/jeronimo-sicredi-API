package com.jeronimo.sicredi_API.dto;

import java.io.Serializable;
import java.util.Date;

import com.jeronimo.sicredi_API.domain.Associate;
import com.jeronimo.sicredi_API.domain.Guideline;
import com.jeronimo.sicredi_API.domain.VotingSession;

public class VotingSessionDTO implements Serializable{
		private static final long serialVersionUID = 1L;
		
		private String id;
		private Date dateStart;
		private Date dateFinish;
		private AssociateDTO associate;
		private GuidelineDTO guideline;
		
		public VotingSessionDTO(VotingSession obj) {
			id = obj.getId();
			dateStart = obj.getDateStart();
			dateFinish = obj.getDateFinish();
			setAssociate(obj.getAssociate());
			setGuideline(obj.getGuideline());
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

		public AssociateDTO getAssociate() {
			return associate;
		}

		public void setAssociate(Associate associate) {
			this.associate = new AssociateDTO(associate);
		}

		public GuidelineDTO getGuideline() {
			return guideline;
		}

		public void setGuideline(Guideline guideline) {
			this.guideline = new GuidelineDTO(guideline);
		}	
		
}
