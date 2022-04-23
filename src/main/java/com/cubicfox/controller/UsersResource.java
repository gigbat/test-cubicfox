package com.cubicfox.controller;


import com.cubicfox.service.api.IntegrationModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersResource {
	@Autowired
	private IntegrationModule integrationModule;

	@GetMapping
	public ResponseEntity<?> saveUsers() {
		return integrationModule.saveIntegrationUsers();
	}
}