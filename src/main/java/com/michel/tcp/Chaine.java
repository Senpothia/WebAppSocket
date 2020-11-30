package com.michel.tcp;

public class Chaine {
	
	private String message = "";
	private boolean change = false;

	public Chaine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chaine(String message, boolean change) {
		super();
		this.message = message;
		this.change = change;
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



}
