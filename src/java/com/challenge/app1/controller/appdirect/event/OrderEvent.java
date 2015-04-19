package com.challenge.app1.controller.appdirect.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "event")
public class OrderEvent extends BaseEvent {

	String flag;	
	OrderPayload payload;
	
	@XmlElement
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@XmlElement
	public OrderPayload getPayload() {
		return payload;
	}

	public void setPayload(OrderPayload payload) {
		this.payload = payload;
	}

	
}
