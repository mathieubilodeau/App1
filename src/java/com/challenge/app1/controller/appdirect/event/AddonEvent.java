package com.challenge.app1.controller.appdirect.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "event")
public class AddonEvent extends BaseEvent {
	AddonPayload payload;
	
	@XmlElement
	public AddonPayload getPayload() {
		return payload;
	}

	public void setPayload(AddonPayload payload) {
		this.payload = payload;
	}
}
