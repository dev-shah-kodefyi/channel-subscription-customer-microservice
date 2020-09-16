package com.mindtree.channel.exception;


public class ExceptionResponse {

	private String errorMessage;
	private String requestURL;
	
	public ExceptionResponse() {
		super();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [errorMessage=" + errorMessage + ", requestURL=" + requestURL + "]";
	}
		
}
