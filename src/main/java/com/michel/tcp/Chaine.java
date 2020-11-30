package com.michel.tcp;

public class Chaine {
	
	private String message = "";
	private boolean change = false;
	private boolean lecture = true;

	public Chaine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chaine(String message, boolean change, boolean lecture) {
		super();
		this.message = message;
		this.change = change;
		this.lecture = lecture;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isChange() {
		return change;
	}

	public void setChange(boolean change) {
		this.change = change;
	}

	public boolean isLecture() {
		return lecture;
	}

	public void setLecture(boolean lecture) {
		this.lecture = lecture;
	}

	


}
