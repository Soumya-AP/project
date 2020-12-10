package com.miniProject.response;

public class ActionResponse {
	
	private int questionnaireId;
	private String title;
	

	public ActionResponse(int questionnaireId, String title) {
		this.questionnaireId = questionnaireId;
		this.title = title;
	}
	
	public int getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(int questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}
