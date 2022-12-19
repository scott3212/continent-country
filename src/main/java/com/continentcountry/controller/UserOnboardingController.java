package com.continentcountry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.continentcountry.entity.User;
import com.continentcountry.repository.IUserRepository;
import com.continentcountry.request.CredentialRequest;
import com.continentcountry.response.CredentialResponse;
import com.continentcountry.service.UserService;
import com.continentcountry.translator.UserCredentialTranslator;
import com.continentcountry.util.ErrorResponseBuilder;
import com.continentcountry.util.JwtUtility;

@RestController
@RequestMapping("/api")
public class UserOnboardingController {
	@Autowired
	IUserRepository userRepository;

	@Autowired
	UserCredentialTranslator userCredentialTranslator;

	@Autowired
	UserService userDetailsService;

	@Autowired
	JwtUtility jwtUtility;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ErrorResponseBuilder errorResponseBuilder;

	@PostMapping("/register")
	public ResponseEntity<CredentialResponse> createUser(@RequestBody CredentialRequest userCredential) {

		User user = userRepository.findByUsername(userCredential.getUsername());
		if (user != null) {
			String errorMsg = "User is already existed. Please try login directly";
			return new ResponseEntity<>(CredentialResponse.builder()
					.error(errorResponseBuilder.toError(errorMsg, 400))
					.build(), HttpStatus.BAD_REQUEST);
		}
		userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
		user = userCredentialTranslator.requestToEntity(userCredential);
		User createdUser = userRepository.save(user);
		final String token = jwtUtility.generateToken(createdUser);
		CredentialResponse response = userCredentialTranslator.entityToResponse(user);
		response.setJwt(token);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<CredentialResponse> loginUser(@RequestBody CredentialRequest userCredential) {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredential.getUsername(),
				userCredential.getPassword()));

		User user = userRepository.findByUsername(userCredential.getUsername());
		final String token = jwtUtility.generateToken(user);
		CredentialResponse response = userCredentialTranslator.entityToResponse(user);
		response.setJwt(token);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
