package com.continentcountry.response;

import java.util.UUID;

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
public class CredentialResponse {
	private UUID uuid;
	private String username;
	private String jwt;
	private ErrorResponse error;
}
