package com.michel.tcp;

import java.time.LocalDateTime;

public class Transfert {
	
	private String commande;
	private String envoi;
	private String acquittement;
	private String retour;
	
	public Transfert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transfert(String commande, String envoi, String acquittement, String retour) {
		super();
		this.commande = commande;
		this.envoi = envoi;
		this.acquittement = acquittement;
		this.retour = retour;
	}

	public String getCommande() {
		return commande;
	}

	public void setCommande(String commande) {
		this.commande = commande;
	}

	public String getEnvoi() {
		return envoi;
	}

	public void setEnvoi(String envoi) {
		this.envoi = envoi;
	}

	public String getAcquittement() {
		return acquittement;
	}

	public void setAcquittement(String acquittement) {
		this.acquittement = acquittement;
	}

	public String getRetour() {
		return retour;
	}

	public void setRetour(String retour) {
		this.retour = retour;
	}


}
