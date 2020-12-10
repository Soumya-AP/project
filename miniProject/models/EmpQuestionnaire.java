package com.miniProject.models;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "emp_questionnaire")
public class EmpQuestionnaire {
	@Id
	@Column(name = "map_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int mapId;

	@Column(name = "quest_id")
	private int questId;

	@Column(name = "emp_id")
	private String empId;

	@Column(name = "status_value")
	private int statusValue;
	
	@Column(name = "mail_sent_date")
	private LocalDate mailSentDate;
	
	@Column(name = "accepted_date")
	private LocalDate acceptedDate;


	public EmpQuestionnaire() {
		
	}
	public EmpQuestionnaire(int mapId,int questId, String empId, int statusValue) {
		this.mapId=mapId;
		this.questId = questId;
		this.empId = empId;
		this.statusValue = statusValue;
	}

	public EmpQuestionnaire(int questId, String empId, int statusValue) {
		this.questId = questId;
		this.empId = empId;
		this.statusValue = statusValue;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public int getQuestId() {
		return questId;
	}

	public void setQuestId(int questId) {
		this.questId = questId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getStatus() {
		return statusValue;
	}

	public void setStatus(int statusValue) {
		this.statusValue = statusValue;
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
