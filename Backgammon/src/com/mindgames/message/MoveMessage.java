package com.mindgames.message;

import com.mindgames.gammon.GammonGame;
import com.mindgames.websocket.BackgammonWebSocket;
import com.mindgames.websocket.BackgammonWebSocketServlet;
import com.mindgames.websocket.Webpackage;

public class MoveMessage extends Message {

	public String type;
	public int from;
	public int to;
	
	MoveMessage(String type, int from, int to){
		this.type = type;
		this.from = from;
		this.to = to;
	}
	
	public void process() throws InterruptedException{
		GammonGame.move(from, to);
		for (BackgammonWebSocket member : BackgammonWebSocketServlet.members)
        {
			String data = BackgammonWebSocket.gson.toJson(new MoveMessage(MoveMessage.class.getSimpleName(), from, to));
			send(new Webpackage(data, member));
        	System.out.println("MoveMessage put");
        }
	}
}
