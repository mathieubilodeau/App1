package com.challenge.app1.controller.appdirect.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "event")
public class CancelEvent extends BaseEvent {
	CancelPayload payload;
	
	@XmlElement
	public CancelPayload getPayload() {
		return payload;
	}

	public void setPayload(CancelPayload payload) {
		this.payload = payload;
	}
	
}
