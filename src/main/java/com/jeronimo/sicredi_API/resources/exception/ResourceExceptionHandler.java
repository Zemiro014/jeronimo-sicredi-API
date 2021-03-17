package com.jeronimo.sicredi_API.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jeronimo.sicredi_API.listener.exception.ObjectNotFoundException;
import com.jeronimo.sicredi_API.listener.exception.VotingNotAllowedException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(VotingNotAllowedException.class)
	public ResponseEntity<StandardError> votingNotAllowed(VotingNotAllowedException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Voto negado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
