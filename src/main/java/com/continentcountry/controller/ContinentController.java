package com.continentcountry.controller;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.continentcountry.response.Continent;
import com.continentcountry.response.ContinentResponse;
import com.continentcountry.response.external.ExternalContinent;
import com.continentcountry.response.external.ExternalCountry;
import com.continentcountry.service.ContinentService;
import com.continentcountry.service.CountryService;
import com.continentcountry.translator.ContinentTranslator;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/continent")
@SecurityRequirement(name = "Authorization")
public class ContinentController {

	@Autowired
	private CountryService countryService;

	@Autowired
	private ContinentService continentService;

	@Autowired
	private ContinentTranslator continentTranslator;

	/**
	 * This is the endpoint that return the othercountries in the same continent. A
	 * Request param "Codes" is required, and the value should be country codes
	 * separated by comma e.g. /api/continent/othercountries?codes=CA,US
	 *
	 * @param codes the value should be country codes separated by comma
	 * @return Other countries in the same continent
	 * @throws ClientProtocolException
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@GetMapping("/othercountries")
	public ResponseEntity<ContinentResponse> getUserInfo(@RequestParam String codes)
			throws ClientProtocolException, URISyntaxException, IOException {
		String[] codeArray = codes.split(",");
		Map<String, Set<String>> continentCountriesMap = new HashMap<>();
		for (String code : codeArray) {
			ExternalCountry country = countryService.getCountryByCode(code);
			String continentCode = country.getContinent().getCode();
			if (continentCountriesMap.get(continentCode) == null) {
				continentCountriesMap.put(continentCode, new HashSet<String>(asList(country.getCode())));
			} else {
				continentCountriesMap.get(continentCode).add(country.getCode());
			}
		}

		List<ExternalContinent> continentList = new ArrayList<>();
		for (String continentCode : continentCountriesMap.keySet()) {
			continentList.add(continentService.getContinentByCode(continentCode));
		}

		List<Continent> listOfContinent = continentList.stream()
				.map(continent -> continentTranslator.toInternal(continent, continentCountriesMap.get(continent
						.getCode())))
				.collect(Collectors.toList());

		return new ResponseEntity<>(ContinentResponse.builder().continent(listOfContinent).build(), HttpStatus.OK);
	}
}
