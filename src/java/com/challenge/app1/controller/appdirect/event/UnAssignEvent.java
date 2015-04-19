package com.challenge.app1.controller.appdirect.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "event")
public class UnAssignEvent extends BaseEvent {
	UnassignPayload payload;
	
	@XmlElement
	public UnassignPayload getPayload() {
		return payload;
	}

	public void setPayload(UnassignPayload payload) {
		this.payload = payload;
	}

}
