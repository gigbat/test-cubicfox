package com.cubicfox.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ControllerTests {
	@Autowired
	private UsersResource resource;

	@Test
	void checkIfControllerIsNullTest() {
		Assertions.assertThat(resource).isNotNull();
	}
}
