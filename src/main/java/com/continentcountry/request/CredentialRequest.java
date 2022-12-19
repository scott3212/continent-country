package com.continentcountry.request;

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
public class CredentialRequest {
	private String username;
	private String password;
}
