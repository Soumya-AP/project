package com.miniProject.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.miniProject.mailService.MailService;
import com.miniProject.models.EmpQuestionnaire;
import com.miniProject.models.Employee;
import com.miniProject.models.Questionnaire;
import com.miniProject.models.Role;
import com.miniProject.repository.EmpQuestionnaireRepo;
import com.miniProject.repository.EmployeeRepository;
import com.miniProject.repository.QuestionnaireRepository;
import com.miniProject.repository.RoleRepository;
import com.miniProject.response.ActionResponse;
import com.miniProject.response.MessageResponse;
import com.miniProject.response.ReportResponse;
import com.miniProject.response.UploadFileResponse;
import com.miniProject.service.EmpQuestionnaireService;
import com.miniProject.service.EmployeeService;
import com.miniProject.service.FileStorageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/questionnaire")
public class QuestionnaireController {

	@Autowired
	private MailService notificationService;

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	QuestionnaireRepository questionnaireRepository;

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmpQuestionnaireService service;
	
	@Autowired
	EmpQuestionnaireRepo repository;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/uploadContentFile/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')")
	public UploadFileResponse uploadContentFile(@RequestParam("file") MultipartFile file,
			@PathVariable Integer questionnaire_id) {
		String fileName = fileStorageService.storeFile(file);

		Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaire_id);
		Questionnaire quest = questionnaire.get();

		String fileDownloadUri = ServletUriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/questionnaire")
				.path("/downloadFile/").path(fileName).toUriString();

		quest.setPptUpload(fileDownloadUri);
		questionnaireRepository.save(quest);

		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@PostMapping("/uploadParticipantFile/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN')or hasRole('ADMIN')")
	public UploadFileResponse uploadParticipantFile(@RequestParam("file") MultipartFile file,
			@PathVariable Integer questionnaire_id) {
		String fileName = fileStorageService.storeFile(file);

		Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaire_id);
		Questionnaire quest = questionnaire.get();

		String fileDownloadUri = ServletUriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/questionnaire")
				.path("/downloadFile/").path(fileName).toUriString();

		quest.setParticipantList(fileDownloadUri);
		questionnaireRepository.save(quest);

		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			System.out.print("Could not determine file type.");
		}

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@PostMapping("/addUsers/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> addUser(@RequestBody List<Employee> employeeList, @PathVariable Integer questionnaire_id) {
		Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaire_id);
		Questionnaire quest = questionnaire.get();
		
		for (Employee emp : employeeList) 
		{
			String id=emp.getId();
			if(employeeRepository.existsById(id))
			{
				service.setData(quest.getQuestionnaireId(), emp.getId(), 0);
			}
			else {
				Set<Role> roles = new HashSet<>();
				Role userRole = roleRepository.findByName("ROLE_USER")
						.orElseThrow(() -> new RuntimeException("Error: Role not found."));
				roles.add(userRole);
				emp.setRoles(roles);
				
				String password = emp.generatePassword(8);
				emp.setPassword(encoder.encode(password));
				employeeRepository.save(emp);
				
				service.setData(quest.getQuestionnaireId(), emp.getId(), 0);
			}
		}
		return ResponseEntity.ok(new MessageResponse("Users are added to the database"));

	}
	
	@PutMapping("/save")
	@PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public Questionnaire saveQuestionnaire(@RequestBody Questionnaire quest) {
		questionnaireRepository.save(quest);
		return quest;
	}

	@PostMapping("/publish/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
	public ResponseEntity<?> publish(@PathVariable Integer questionnaire_id) {

		try {
			List<EmpQuestionnaire> emp_quest = repository.findByQuestionnaireId(questionnaire_id);
			for (EmpQuestionnaire mapping : emp_quest) {
				mapping.setMailSentDate(LocalDate.now());
				repository.save(mapping);
			}
			
			Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaire_id);
			Questionnaire quest = questionnaire.get();
			List<String> empId=repository.getUser(questionnaire_id);
			for (String list :empId ) {
				Employee employee=employeeRepository.findById(list).get();
				String password = employee.generatePassword(8);
				employee.setPassword(encoder.encode(password));
				employeeRepository.save(employee);

				String subject = "Questionnaire " + quest.getQuestionnaireId();
				String text = quest.getMailBody() + "\nPassword: " + password + "\nUsername: " + employee.getUsername();
				notificationService.sendEmail(employee, subject, text);
				
			}

			return ResponseEntity.ok(new MessageResponse("Questionnaire is published and Mail sent successfully"));
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return null;
	}
	
	@PostMapping("/remind/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')") 
	public ResponseEntity<?> reminder(@PathVariable Integer questionnaire_id) {

		try {
			Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaire_id);
			Questionnaire quest = questionnaire.get();
			List<String> list=repository.getEmployee(questionnaire_id);
			for (int i=0;i<list.size();i++) {
				Optional<Employee> employee = employeeRepository.findById(list.get(i));
				Employee emp=employee.get();
				

				String password = emp.generatePassword(8);
				emp.setPassword(encoder.encode(password));
				employeeRepository.save(emp);
				
				String subject = "Pending to accept Questionnaire " + quest.getQuestionnaireId();
				String text = quest.getMailBody() + "\nPassword: " + password + "\nUsername: " + emp.getUsername();
				notificationService.sendEmail(emp, subject, text);
			}

			return ResponseEntity.ok(new MessageResponse("Reminder Mail sent successfully"));
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return null;
	}

	@GetMapping("/displayQuestionnaire/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public Questionnaire display(@PathVariable Integer questionnaire_id) {
		Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaire_id);
		Questionnaire quest = questionnaire.get();	
		return quest;
	}
	
	@GetMapping("/generateReport/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public List<ReportResponse> generateReport(@PathVariable int questionnaire_id) {
		
		List<EmpQuestionnaire> list = service.findByQuestionnaireId(questionnaire_id);
		List<ReportResponse> reports= new ArrayList<ReportResponse>();
		for(EmpQuestionnaire emp_quest:list) {
			String emp_id=emp_quest.getEmpId();
			Optional<Employee> employee= employeeRepository.findById(emp_id);
			String name = employee.get().getUsername();
			int statusValue=emp_quest.getStatus();
			String status="";
			if(statusValue==1)
			{
				status="Accepted";
			}
			else
				status="Not Accepted";
				
			reports.add(new ReportResponse(emp_id,name,status,emp_quest.getMailSentDate(),emp_quest.getAcceptedDate()));	 
		}
		return reports;
		
	}
	
	@PutMapping("/accept/{questionnaire_id}")
	@PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN') or hasRole('USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> agree(@PathVariable Integer questionnaire_id) {
		Employee emp = employeeService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
		Optional<Questionnaire> questionnaire = questionnaireRepository.findById(questionnaire_id);
		Questionnaire quest = questionnaire.get();
		
		int id = service.getMapId(quest.getQuestionnaireId(), emp.getId());
		EmpQuestionnaire emp_ques = repository.getOne(id);
		emp_ques.setStatus(1);
		emp_ques.setAcceptedDate(LocalDate.now());
		repository.save(emp_ques);
		
		return ResponseEntity.ok(new MessageResponse("Thank you for accepting!"));

	}
	
	@GetMapping("/completedQuestionnaire/{empId}")
	@PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN') or hasRole('USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public List<ActionResponse> completedQuestionnaire(@PathVariable String empId) {
		
		List<ActionResponse> reports= new ArrayList<ActionResponse>();
		List<Integer> results=service.getQuestionnaireStatus(empId, 1);
		for(int i=0;i<results.size();i++)
		{
			int id=results.get(i);
			Questionnaire quest= questionnaireRepository.getOne(id);
			reports.add(new ActionResponse(quest.getQuestionnaireId(),quest.getTitle()));
		}
		return reports;
	}
	
	@GetMapping("/pendingQuestionnaire/{empId}")
	@PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN') or hasRole('USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public List<ActionResponse> pendingQuestionnaire(@PathVariable String empId) {
		List<Integer> results= service.getQuestionnaireStatus(empId, 0);
		List<ActionResponse> reports= new ArrayList<ActionResponse>();
		for(int i=0;i<results.size();i++)
		{
			int id=results.get(i);
			Questionnaire quest= questionnaireRepository.getOne(id);
			reports.add(new ActionResponse(quest.getQuestionnaireId(),quest.getTitle()));
		}
		return reports;
	}

}
