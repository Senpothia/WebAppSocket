package com.michel.tcp;

import java.time.LocalDateTime;
import java.util.Date;

public class connexion {
	
	private Imei imei;
	private String ip;
	private Date date;
	
	public connexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public connexion(Imei imei, String ip, Date date) {
		super();
		this.imei = imei;
		this.ip = ip;
		this.date = date;
	}

	public Imei getImei() {
		return imei;
	}

	public void setImei(Imei imei) {
		this.imei = imei;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

}
