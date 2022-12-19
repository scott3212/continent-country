package com.continentcountry.translator;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.continentcountry.response.Continent;
import com.continentcountry.response.external.ExternalContinent;
import com.continentcountry.response.external.ExternalCountry;

@Component
public class ContinentTranslator {

	public Continent toInternal(ExternalContinent continent, Set<String> countries) {
		return Continent.builder()
				.countries(countries)
				.name(continent.getName())
				.otherCountries(continent.getCountries().stream()
						.map(ExternalCountry::getCode)
						.filter(country -> !countries.contains(country))
						.collect(Collectors.toList()))
				.build();
	}
}
