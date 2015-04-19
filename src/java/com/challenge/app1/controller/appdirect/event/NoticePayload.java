package com.challenge.app1.controller.appdirect.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class NoticePayload {
	Account account;
	Notice notice;	
	
	@XmlElement
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}	
	
	@XmlElement
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	
	
}
