package com.mindgames.message;

import com.mindgames.websocket.BackgammonWebSocket;
import com.mindgames.websocket.BackgammonWebSocketServlet;
import com.mindgames.websocket.Webpackage;

public class JoinMessage extends Message {
	public String type;
	public String who;
	
	JoinMessage(String type, String who){
		this.type = type;
		this.who = who;
	}
	
	@Override
	public void process() throws InterruptedException {
		for (BackgammonWebSocket member : BackgammonWebSocketServlet.members)
        {
			String data = BackgammonWebSocket.gson.toJson(new JoinMessage(JoinMessage.class.getSimpleName(), who));
			send(new Webpackage(data, member));
        	System.out.println("JoinMessage put");
        }
	}
}
