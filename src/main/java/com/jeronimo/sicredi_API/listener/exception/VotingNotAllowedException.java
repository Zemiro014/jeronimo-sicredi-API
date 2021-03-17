package com.jeronimo.sicredi_API.listener.exception;

public class VotingNotAllowedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public VotingNotAllowedException(String msg) {
		super(msg);
	}

}
