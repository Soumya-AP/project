package com.miniProject.response;

import java.time.LocalDate;

public class ReportResponse {

	private String empId;
	private String username;
	private String status;
    private LocalDate mailSentDate; 
	private LocalDate acceptedDate;
	
	public ReportResponse(String empId, String username, String status,LocalDate mailSentDate,LocalDate acceptedDate) {
		this.empId = empId;
		this.username = username;
		this.status = status;
		this.mailSentDate=mailSentDate;
		this.acceptedDate=acceptedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public LocalDate getMailSentDate() {
		return mailSentDate;
	}

	public void setMailSentDate(LocalDate mailSentDate) {
		this.mailSentDate = mailSentDate;
	}

	public LocalDate getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(LocalDate acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
	
}