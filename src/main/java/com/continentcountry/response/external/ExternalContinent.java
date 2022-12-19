package com.continentcountry.response.external;

import java.util.List;

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
public class ExternalContinent {
	private String code;
	private String name;
	private List<ExternalCountry> countries;
}
