package com.fees.processor.exception;

public class FeeProcessingException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private Object objectInIssue;

	public FeeProcessingException(){
		super();
	}
	
	public FeeProcessingException(String argMessage){
		super(argMessage);
	}
	
	public FeeProcessingException(String argMessage, Object objectInIssue) {
		super(argMessage);
		this.objectInIssue = objectInIssue;
	}

	@Override
	public String toString() {
		return "FeeProcessingException [objectInIssue=" + objectInIssue + "]";
	}
}
