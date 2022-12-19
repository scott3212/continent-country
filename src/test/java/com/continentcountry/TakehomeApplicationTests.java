package com.continentcountry;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.endpoint.ApiVersion;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TakehomeApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(ApiVersion.LATEST.equals(ApiVersion.V3));
	}
}
