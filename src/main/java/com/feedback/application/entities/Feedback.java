package com.feedback.application.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection="feedback")
public class Feedback {
	
	@Id
	private String id_f;
	
	private Object user_model_f;
	
	private String feedback_text_f;
	
	private int note_f;

	public Feedback(UserModel user_model_f, String feedback_text_f, int note_f) {
		super();
		this.user_model_f = user_model_f;
		this.feedback_text_f = feedback_text_f;
		this.note_f = note_f;
	}

	public Feedback() {
		super();
	}

	public String getId_f() {
		return id_f;
	}

	public void setId_f(String id_f) {
		this.id_f = id_f;
	}

	public Object getUser_model_f() {
		return user_model_f;
	}

	public void setUser_model_f(Object user_model_f) {
		this.user_model_f = user_model_f;
	}

	public String getFeedback_text_f() {
		return feedback_text_f;
	}

	public void setFeedback_text_f(String feedback_text_f) {
		this.feedback_text_f = feedback_text_f;
	}

	public int getNote_f() {
		return note_f;
	}

	public void setNote_f(int note_f) {
		this.note_f = note_f;
	}

}
