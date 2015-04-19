package com.challenge.app1.model;

import java.util.Date;

//@Entity
public class Event {

	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// private Long id;

	private Date timestamp_;
	private String type_;
	private String accountIdentifier_;
	private String email_;

	public String getType() {
		return type_;
	}

	public void setType(String type_) {
		this.type_ = type_;
	}

	public Event() {
		setTimestamp(new Date());
	}

	public Date getTimestamp() {
		return timestamp_;
	}

	public void setTimestamp(Date timestamp) {
		timestamp_ = timestamp;
	}

	public String getAccountIdentifier() {
		return accountIdentifier_;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier_ = accountIdentifier;
	}

	public String getEmail() {
		return email_;
	}

	public void setEmail(String email) {
		email_ = email;
	}

}
