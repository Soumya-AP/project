package com.miniProject.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Login")
public class LoginController {

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('SUPERADMIN') or hasRole('ADMIN')")
	public String userAccess() {
		return "Viewing User Dashboard";
	}

	@GetMapping("/superAdmin")
	@PreAuthorize("hasRole('SUPERADMIN')")
	public String moderatorAccess() {
		return "Viewing Super admin content";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Viewing Admin content";
	}
	
}