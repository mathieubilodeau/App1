package com.challenge.app1.controller.appdirect.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class AddonPayload {
	Account account;
	AddonOrder order;	
	
	@XmlElement
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	@XmlElement
	public AddonOrder getOrder() {
		return order;
	}
	public void setOrder(AddonOrder order) {
		this.order = order;
	}
}
