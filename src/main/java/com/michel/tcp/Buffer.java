package com.michel.tcp;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.michel.tcp.socket.ServerTcp;

public class Buffer extends Observable {

	private String code = "0";
	private boolean change = false;

	public Buffer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Buffer(String code) {
		super();
		this.code = code;
	}

	public String getCode() {

		return code;
	}

	public void setCode(String code) {
		this.code = code;
		this.change = true;
		setChanged();
		notifyObservers();
	}
	
	
	public boolean isChange() {
		return change;
	}

	public void setChange(boolean change) {
		this.change = change;
	}

	public Long testHex() {

		long decimal = Long.parseLong(this.code, 16);
		return decimal;

	}

}
