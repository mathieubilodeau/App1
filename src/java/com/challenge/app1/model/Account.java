package com.challenge.app1.model;

//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

public class Account {

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// @Column
	private String email;

	private String editionCode_;
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEditionCode(String editionCode) {
		editionCode_ = editionCode;
	}
	
	public String getEditionCode() {
		return editionCode_;
	}

	public String getAccountIdentifier() {
		return String.valueOf(getId());
	}

}
