package com.cubicfox.controller;


import com.cubicfox.client.api.UserIntegrationService;
import com.cubicfox.model.User;
import com.cubicfox.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersResource {
	@Autowired
	private UserIntegrationService userIntegrationService;
	@Autowired
	private UserService userService;

	@GetMapping
	public String saveUsers() {
		List<User> integrationUsers = userIntegrationService.getUsers();
		List<User> users = userService.saveUsers(integrationUsers);
		return "Users have been saved successfully!";
	}
}