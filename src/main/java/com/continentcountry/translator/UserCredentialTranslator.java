package com.continentcountry.translator;

import org.springframework.stereotype.Component;

import com.continentcountry.entity.User;
import com.continentcountry.request.CredentialRequest;
import com.continentcountry.response.CredentialResponse;

@Component
public class UserCredentialTranslator {

	public CredentialResponse entityToResponse(User entity) {
		return CredentialResponse.builder()
				.username(entity.getUsername())
				.uuid(entity.getUuid())
				.build();
	}

	public User requestToEntity(CredentialRequest request) {
		return User.builder()
				.username(request.getUsername())
				.password(request.getPassword())
				.build();
	}
}
