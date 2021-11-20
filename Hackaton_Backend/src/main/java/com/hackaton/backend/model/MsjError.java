package com.hackaton.backend.model;

public class MsjError {
	private int success = 0;
	private String message;
	private String title;
	
	public MsjError(String title, String message) {
		super();
		this.message = message;
		this.title = title;
	}
	
	public MsjError(int success, String title, String message) {
		super();
		this.success = success;
		this.message = message;
		this.title = title;
	}
	
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
