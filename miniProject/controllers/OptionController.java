package com.miniProject.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.miniProject.mailService.MailService;
import com.miniProject.models.Employee;
import com.miniProject.models.Role;
import com.miniProject.repository.EmployeeRepository;
import com.miniProject.repository.RoleRepository;
import com.miniProject.response.EmployeeResponse;
import com.miniProject.response.MessageResponse;
import com.miniProject.service.EmployeeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/option")
public class OptionController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private MailService notificationService;


	@PostMapping("/addAdmin")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> addAdmin(@RequestBody Employee emp) {
		try {
	
		Set<Role> roles = new HashSet<>();
		Role adminRole = roleRepository.findByName("ROLE_ADMIN")
				.orElseThrow(() -> new RuntimeException("Error: Role not found."));
		roles.add(adminRole);
		emp.setRoles(roles);
		String password = emp.generatePassword(8);
		emp.setPassword(encoder.encode(password));
		employeeRepository.save(emp);
		
		String subject = "You have been added as an Admin";
		String text = "Hello,\nYou have been added as an Admin. Please login using the below given credentials." 
					+ "\nPassword: " + password + "\nUsername: " + emp.getUsername()+ "\nRegards";
		notificationService.sendEmail(emp, subject, text);
		
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return ResponseEntity.ok(new EmployeeResponse(emp.getId(), emp.getUsername(),
				emp.getEmail(), emp.getRoles()));

	}
	
	@PostMapping("/addSuperAdmin")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> addSuperAdmin(@RequestBody Employee emp) {
		try {
			
		Set<Role> roles = new HashSet<>();
		Role superAdminRole = roleRepository.findByName("ROLE_SUPERADMIN")
				.orElseThrow(() -> new RuntimeException("Error: Role not found."));
		roles.add(superAdminRole);
		emp.setRoles(roles);
		String password = emp.generatePassword(8);
		emp.setPassword(encoder.encode(password));
		employeeRepository.save(emp);
		
		String subject = "You have been added as Super Admin";
		String text = "Hello,\nYou have been added as Super Admin. Please login using the below given credentials." 
					+ "\nPassword: " + password + "\nUsername: " + emp.getUsername()+ "\nRegards";
		notificationService.sendEmail(emp, subject, text);
		
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return ResponseEntity.ok(new EmployeeResponse(emp.getId(), emp.getUsername(),
				emp.getEmail(), emp.getRoles()));

	}
	
	@GetMapping("/viewAdmin")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public List<EmployeeResponse> viewAdmin() {
		
		List<EmployeeResponse> list = new ArrayList<EmployeeResponse>();
		List<Employee> employeelist = employeeRepository.getAdmins();
		for(Employee emp: employeelist)
		{
			
			list.add(new EmployeeResponse(emp.getId(), emp.getUsername(), emp.getEmail(),emp.getRoles()));
		}
		return list;
	}
	
	@GetMapping("/viewSuperAdmin")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public List<EmployeeResponse> viewSuperAdmin()
	{
		List<EmployeeResponse> list = new ArrayList<EmployeeResponse>();
		List<Employee> employeelist = employeeRepository.getSuperAdmins();
		for(Employee emp: employeelist)
		{
			
			list.add(new EmployeeResponse(emp.getId(), emp.getUsername(), emp.getEmail(),emp.getRoles()));
		}
		return list;
	}
	
	
	@PutMapping("/updateAdmin/{id}")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateAdmin(@RequestBody Employee emp,@PathVariable String id) {
		Set<Role> role = new HashSet<>();
		Role adminRole = roleRepository.findByName("ROLE_ADMIN")
				.orElseThrow(() -> new RuntimeException("Error: Role not found."));
		role.add(adminRole);
		emp.setRoles(role);
		emp.setId(id);
		//String password = emp.generatePassword(8);
		//emp.setPassword(encoder.encode(password));
		employeeRepository.save(emp);
		return ResponseEntity.ok(new EmployeeResponse(emp.getId(), emp.getUsername(),
				emp.getEmail(), emp.getRoles()));
	}	
	
	@PutMapping("/updateSuperAdmin/{id}")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>updateSuperAdmin(@RequestBody Employee emp,@PathVariable String id) {
		Set<Role> role = new HashSet<>();
		Role superAdminRole = roleRepository.findByName("ROLE_SUPERADMIN")
				.orElseThrow(() -> new RuntimeException("Error: Role not found."));
		role.add(superAdminRole);
		emp.setRoles(role);
		emp.setId(id);
		//String password = emp.generatePassword(8);
		//emp.setPassword(encoder.encode(password));
		employeeRepository.save(emp);
		return ResponseEntity.ok(new EmployeeResponse(emp.getId(), emp.getUsername(),
				emp.getEmail(), emp.getRoles()));	
	}
	
	@DeleteMapping("/deleteAdmin/{id}")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	public ResponseEntity<?> deleteAdmin(@PathVariable("id") String id) {
		 try {
			 employeeRepository.deleteById(id);
			 return ResponseEntity.ok(new MessageResponse("Admin with Employee Id "+id+" Deleted"));
		 }catch(NoSuchElementException e) {
			 return ResponseEntity.ok(new MessageResponse("NOT FOUND"));
		 }
	}
	
	@DeleteMapping("/deleteSuperAdmin/{id}")
	@PreAuthorize("hasRole('SUPERADMIN')")
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	public ResponseEntity<?> deleteSuperAdmin(@PathVariable("id") String id) {
		 try {
			 employeeRepository.deleteById(id);
			 return ResponseEntity.ok(new MessageResponse("Super Admin with Employee Id "+id+" Deleted"));
			 
		 }catch(NoSuchElementException e) {
			 return ResponseEntity.ok(new MessageResponse("NOT FOUND"));
		 }
	}
	
	@PutMapping("/changePassword")
	@PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')" )
	public ResponseEntity<?> changePassword(@RequestParam("Password") String password, @RequestParam("Re-enter") String password1) {
	    Employee emp = employeeService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
	    if(password.equals(password1)) {
	    	emp.setPassword(encoder.encode(password));
			employeeRepository.save(emp);
	    	return ResponseEntity.ok(new MessageResponse("Password updated successfully"));
	    }
	    return null;
	    
	}

}
