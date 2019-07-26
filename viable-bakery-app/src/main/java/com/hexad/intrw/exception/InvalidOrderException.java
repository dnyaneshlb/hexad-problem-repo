package com.hexad.intrw.exception;

public class InvalidOrderException extends BakeryException{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidOrderException(String message){
		super(message);
	}
	
	public InvalidOrderException(String message, RuntimeException ex){
		super(message, ex);
	}
}
