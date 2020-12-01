package com.michel.tcp;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Commande {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String syntaxe;
	private String description;
	
	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commande(Integer id, String syntaxe, String description) {
		super();
		this.id = id;
		this.syntaxe = syntaxe;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSyntaxe() {
		return syntaxe;
	}

	public void setSyntaxe(String syntaxe) {
		this.syntaxe = syntaxe;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
