package com.continentcountry.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

@Service
public class GraphQLService {
	public String call(String url, String query) throws URISyntaxException, ClientProtocolException,
			IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		URI uri = new URIBuilder(request.getURI())
				.addParameter("query", query)
				.build();
		request.setURI(uri);
		HttpResponse httpResponse = client.execute(request);
		return IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8.name());
	}
}
