package com.mindgames.websocket;

import com.mindgames.websocket.QueueHandler;

public class GetFromQueue {

	 public static void main(String[] args) throws InterruptedException 
	 {
		 Webpackage message = QueueHandler.queue.take();
	     System.out.println("Got message from queue " + message);
	 }
}
