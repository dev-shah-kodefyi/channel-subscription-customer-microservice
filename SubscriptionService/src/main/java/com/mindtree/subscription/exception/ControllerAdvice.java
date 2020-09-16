package com.mindtree.subscription.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(ServiceException.class)
	public ExceptionResponse handleAppException(final ServiceException exception, final HttpServletRequest request)
	{
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setRequestURL(request.getRequestURI());
		
		return error;
	}
}
