package com.continentcountry.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.continentcountry.response.ErrorResponse;
import com.continentcountry.util.ErrorResponseBuilder;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	ErrorResponseBuilder errorResponseBuilder;

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorWrapper> handleExceptions(BadCredentialsException exception,
			WebRequest webRequest) {
		String errorMsg = "Username or password is incorrect";
		log.error("Authentication error", exception);
		ErrorResponse response = errorResponseBuilder.toError(errorMsg, 403);
		ErrorWrapper error = ErrorWrapper.builder().error(response).build();
		ResponseEntity<ErrorWrapper> entity = new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
		return entity;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorWrapper> handleExceptions(Exception exception, WebRequest webRequest) {
		String errorMsg = "Unknown error, check log for details";
		log.error("Unknown error, see stack trace for details", exception);
		ErrorResponse response = errorResponseBuilder.toError(errorMsg, 500);
		ErrorWrapper error = ErrorWrapper.builder().error(response).build();
		ResponseEntity<ErrorWrapper> entity = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
}

@Builder
@Data
class ErrorWrapper {
	ErrorResponse error;
}
