package com.continentcountry.translator;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.continentcountry.entity.User;
import com.continentcountry.request.CredentialRequest;
import com.continentcountry.response.CredentialResponse;

public class UserCredentialTranslatorTest {
	private UserCredentialTranslator subject;
	private final String USERNAME = "scott3212";
	private final String PASSWORD = "123456";

	@BeforeEach
	public void setup() {
		subject = new UserCredentialTranslator();
	}

	@Test
	public void testTranslateToResponse() {
		UUID uuid = UUID.randomUUID();
		User input = User.builder().username(USERNAME).password(PASSWORD).uuid(uuid).build();

		CredentialResponse response = subject.entityToResponse(input);

		Assertions.assertThat(response.getUsername()).isEqualTo(USERNAME);
		Assertions.assertThat(response.getUuid()).isEqualTo(uuid);
	}

	@Test
	public void testTranslateToEntity() {
		CredentialRequest input = CredentialRequest.builder().username(USERNAME).password(PASSWORD).build();

		User response = subject.requestToEntity(input);

		Assertions.assertThat(response.getUsername()).isEqualTo(USERNAME);
		Assertions.assertThat(response.getPassword()).isEqualTo(PASSWORD);
		Assertions.assertThat(response.getUuid()).isNull();// UUID shouldn't get generated in translator
	}
}
