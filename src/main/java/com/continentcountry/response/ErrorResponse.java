package com.continentcountry.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class ErrorResponse {
	private String errorMsg;
	private int errorCode;
}
