package com.continentcountry.translator;

import org.springframework.stereotype.Component;

import com.continentcountry.entity.User;
import com.continentcountry.request.CredentialRequest;
import com.continentcountry.response.CredentialResponse;

@Component
public class UserCredentialTranslator implements ObjectTranslator<User, CredentialRequest, CredentialResponse> {

	@Override
	public CredentialResponse entityToResponse(User entity) {
		return CredentialResponse.builder()
				.username(entity.getUsername())
				.uuid(entity.getUuid())
				.build();
	}

	@Override
	public User requestToEntity(CredentialRequest request) {
		return User.builder()
				.username(request.getUsername())
				.password(request.getPassword())
				.build();
	}
}
