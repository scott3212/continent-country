package com.continentcountry.response;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Jacksonized
public class Continent {
	private Set<String> countries;
	private String name;
	private List<String> otherCountries;
}
