package com.mindgames.websocket;
import java.util.concurrent.LinkedBlockingQueue;


public class QueueHandler {

	 public static LinkedBlockingQueue<Webpackage> queue;
	
	 public static LinkedBlockingQueue<Webpackage> getQueue(){
		 if (queue == null){
			 queue = new LinkedBlockingQueue<Webpackage>();
		 }
		 return queue;
	 }
}
