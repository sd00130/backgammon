package com.mindgames.websocket;

import java.io.Serializable;

public class Webpackage implements Serializable {

	private String message;
	private BackgammonWebSocket to;
		
	public Webpackage() {

	}
	public Webpackage(String message, BackgammonWebSocket to) {
		this.message = message;
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BackgammonWebSocket getTo() {
		return to;
	}
	public void setTo(BackgammonWebSocket to) {
		this.to = to;
	}
}
