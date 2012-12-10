package com.mindgames.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindgames.websocket.BackgammonWebSocket;
import com.mindgames.websocket.Webpackage;
import com.mindgames.websocket.QueueHandler;
import com.mindgames.websocket.TestMessage;

public class PutServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		try {
			QueueHandler.getQueue().put(new Webpackage("message", new BackgammonWebSocket(null)));
			System.out.println("Message put");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
	}

}
