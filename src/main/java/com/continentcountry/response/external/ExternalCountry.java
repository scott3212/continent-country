package com.continentcountry.response.external;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@Getter
public class ExternalCountry {
	private String code;
	private String name;
	private ExternalContinent continent;
}
