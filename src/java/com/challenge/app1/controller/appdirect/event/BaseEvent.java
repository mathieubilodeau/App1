package com.challenge.app1.controller.appdirect.event;

import javax.xml.bind.annotation.XmlElement;

public class BaseEvent {
	String type;
	MarketPlace marketPlace;
	Creator creator;
	
	@XmlElement(name="marketplace")
	public MarketPlace getMarketPlace() {
		return marketPlace;
	}

	public void setMarketPlace(MarketPlace marketPlace) {
		this.marketPlace = marketPlace;
	}

	@XmlElement
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement
	public Creator getCreator() {
		return creator;
	}

	public void setCreator(Creator creator) {
		this.creator = creator;
	}
}
