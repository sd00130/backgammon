package com.mindgames.websocket;

public class TestMessage {

	private String message;
	private String to;
	
	public TestMessage(String message, String to) {
		this.message = message;
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
}
