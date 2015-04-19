package com.challenge.app1.controller.appdirect.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "event")
public class AssignEvent extends BaseEvent {
	AssignPayload payload;
	
	@XmlElement
	public AssignPayload getPayload() {
		return payload;
	}

	public void setPayload(AssignPayload payload) {
		this.payload = payload;
	}
}
