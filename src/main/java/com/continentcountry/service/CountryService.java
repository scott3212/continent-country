package com.continentcountry.service;

import static com.continentcountry.constant.Constants.CONTINENT_GRAPHQL_URL;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.continentcountry.response.external.ExternalCountry;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CountryService {

	@Autowired
	private GraphQLService graphQLService;

	@Autowired
	private ObjectMapper objectMapper;

	private final String QUERY = "{country(code:\"%s\"){code,name,continent{code,name}}}";

	public ExternalCountry getCountryByCode(String code) throws ClientProtocolException, URISyntaxException,
			IOException {
		String query = String.format(QUERY, code);
		String response = graphQLService.call(CONTINENT_GRAPHQL_URL, query);
		JsonNode data = objectMapper.readTree(response).get("data").get("country");
		return objectMapper.treeToValue(data, ExternalCountry.class);
	}
}
