package com.hexad.intrw.exception;

public class BakeryException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public BakeryException(String message){
		super(message);
	}
	
	public BakeryException(String message, RuntimeException ex){
		super(message, ex);
	}
}
