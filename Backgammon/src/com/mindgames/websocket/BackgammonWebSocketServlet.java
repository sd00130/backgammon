package com.mindgames.websocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

@SuppressWarnings("serial")
public class BackgammonWebSocketServlet extends WebSocketServlet{

	public static final Set<BackgammonWebSocket> members = new CopyOnWriteArraySet<BackgammonWebSocket>();
    public static final Set<LinkedBlockingQueue<Webpackage>> queues = new CopyOnWriteArraySet<LinkedBlockingQueue<Webpackage>>();
	public static final LinkedBlockingQueue<Webpackage> outgoing = new LinkedBlockingQueue<Webpackage>();
    
    
    public BackgammonWebSocketServlet() throws InterruptedException {
    	new Sender(outgoing).start();
    	queues.add(outgoing);
    }


	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
		BackgammonWebSocket socket = new BackgammonWebSocket(members);
		return socket;
	}
}
