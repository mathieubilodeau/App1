package com.challenge.app1.controller.appdirect.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class AssignPayload {
	Account account;
	User user;	
	
	@XmlElement
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	@XmlElement
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
