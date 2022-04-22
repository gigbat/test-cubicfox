package com.cubicfox.controller;


import com.cubicfox.service.api.IntegrationModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsersResource {
	@Autowired
	private IntegrationModule integrationModule;

	@GetMapping
	public String saveUsers() {
		integrationModule.saveIntegrationUsers();
		return "Users have been saved successfully!";
	}
}