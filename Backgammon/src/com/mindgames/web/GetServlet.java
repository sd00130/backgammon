package com.mindgames.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mindgames.websocket.Webpackage;
import com.mindgames.websocket.QueueHandler;
import com.mindgames.websocket.TestMessage;

public class GetServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		try {
			Object o = QueueHandler.getQueue().take();
			Webpackage t = (Webpackage)o;
			System.out.println("Got message from queue " + t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
