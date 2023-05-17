package com.smedia.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.smedia.dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,WebRequest webrequest){
		ErrorDetails errordetails=new ErrorDetails(new Date(),exception.getMessage(),webrequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errordetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(sMediaException.class)
	public ResponseEntity<ErrorDetails> handlesMediaException(sMediaException exception,WebRequest webrequest){
            ErrorDetails errordetails=new ErrorDetails(new Date(),exception.getMessage(),webrequest.getDescription(false));
		
		    return new ResponseEntity<ErrorDetails>(errordetails,HttpStatus.NOT_FOUND);  
		
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDetails> handleRuntimeException(RuntimeException exception,WebRequest webrequest){
         ErrorDetails errordetails=new ErrorDetails(new Date(),exception.getMessage(),webrequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errordetails,HttpStatus.NOT_FOUND);
	}
}