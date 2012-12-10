package com.mindgames.message;

import java.util.concurrent.LinkedBlockingQueue;

import com.mindgames.websocket.BackgammonWebSocketServlet;
import com.mindgames.websocket.Webpackage;

public abstract class Message {

	public abstract void process() throws InterruptedException;
	
	public void send(Webpackage message) throws InterruptedException{
		for (LinkedBlockingQueue<Webpackage> linkedBlockingQueue : BackgammonWebSocketServlet.queues) {
			linkedBlockingQueue.put(message);
		}
	}
}
