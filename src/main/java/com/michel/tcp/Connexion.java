package com.michel.tcp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Connexion {
	
	//private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	private Imei imei;
	private String ip;
	private LocalDateTime date;
	private String dateTexte;
	private boolean autorisation;
	
	public Connexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Connexion(Imei imei, String ip, LocalDateTime date, boolean autorisation) {
		super();
		this.imei = imei;
		this.ip = ip;
		this.date = date;
		this.dateTexte = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
		this.autorisation = autorisation;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getDateTexte() {
		return dateTexte;
	}

	public void setDateTexte(String dateTexte) {
		this.dateTexte = dateTexte;
	}

	public boolean isAutorisation() {
		return autorisation;
	}

	public void setAutorisation(boolean autorisation) {
		this.autorisation = autorisation;
	}
	
	

	
}
