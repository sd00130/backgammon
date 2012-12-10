package com.mindgames.message;

import com.mindgames.websocket.BackgammonWebSocket;
import com.mindgames.websocket.BackgammonWebSocketServlet;
import com.mindgames.websocket.Webpackage;

public class ChatMessage extends Message {

	public String type;
	public String who;
	public String body;
	
	ChatMessage(String type, String who, String body){
		this.type = type;
		this.who = who;
		this.body = body;
	}
	
	@Override
	public void process() throws InterruptedException {
		for (BackgammonWebSocket member : BackgammonWebSocketServlet.members)
        {
			String data = BackgammonWebSocket.gson.toJson(new ChatMessage(ChatMessage.class.getSimpleName(), who, body));
			send(new Webpackage(data, member));
        	System.out.println("ChatMessage put");
        }
	}

}
