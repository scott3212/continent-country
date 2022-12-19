package com.continentcountry.util;

import org.springframework.stereotype.Component;

import com.continentcountry.response.ErrorResponse;

@Component
public class ErrorResponseBuilder {
	public ErrorResponse toError(String msg, int httpErrorCode) {
		return ErrorResponse.builder()
				.errorMsg(msg)
				.errorCode(httpErrorCode)
				.build();
	}
}
