package com.challenge.app1.controller.appdirect.result;

import javax.xml.bind.annotation.XmlElement;

public class Result {
	String success;
	String errorCode;
	String message;
	
	@XmlElement
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	
	@XmlElement
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	@XmlElement
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
