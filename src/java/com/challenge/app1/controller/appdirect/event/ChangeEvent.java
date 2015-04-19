package com.challenge.app1.controller.appdirect.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "event")
public class ChangeEvent extends BaseEvent{
	String returnUrl;
	ChangePayload payload;
	
	@XmlElement
	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	@XmlElement
	public ChangePayload getPayload() {
		return payload;
	}

	public void setPayload(ChangePayload payload) {
		this.payload = payload;
	}
	
}
