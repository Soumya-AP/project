package com.miniProject.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniProject.mailService.MailService;
import com.miniProject.models.Employee;
import com.miniProject.repository.EmployeeRepository;
import com.miniProject.repository.RoleRepository;
import com.miniProject.request.LoginRequest;
import com.miniProject.response.JwtResponse;
import com.miniProject.response.MessageResponse;
import com.miniProject.security.jwt.JwtUtils;
import com.miniProject.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private MailService notificationService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
					userDetails.getEmail(), roles));	
	}
	
	@PutMapping("/forgotPassword/{id}")
	public ResponseEntity<?> forgotPassword(@PathVariable String id) {
		
		Optional<Employee> employee=employeeRepository.findById(id);
		Employee emp=employee.get();
		
		String password = emp.generatePassword(8);
		String subject= "Password Reset";
		String text="New Password: "+password;
		emp.setPassword(encoder.encode(password));
		employeeRepository.save(emp);
		try {
			notificationService.sendEmail(emp,subject,text);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return ResponseEntity.ok(new MessageResponse("Password has been reset"));
	}
}