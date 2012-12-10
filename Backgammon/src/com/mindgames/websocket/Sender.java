package com.mindgames.websocket;

import java.util.concurrent.LinkedBlockingQueue;

public class Sender extends Thread {

	 public final LinkedBlockingQueue<Webpackage> outgoing;
	 
	public Sender(LinkedBlockingQueue<Webpackage> outgoing) {
		super();
		this.outgoing = outgoing;
		this.setName("Sender Thread");
	}

	@Override
	public void run() {

		    	while(true){
	    		try {
	    			Object o = outgoing.take();
	    			Webpackage webpackage = (Webpackage)o;
	    			System.out.println("Got message from queue " + webpackage);
	    			if (webpackage.getTo().connection == null){
	    				System.out.println("Member's outbound is null");
	    				continue;
	    			}
	    			if(!webpackage.getTo().connection.isOpen()){
	    				System.out.println("Member's outbound is not opened");
	    				continue;
	    			}
	    			webpackage.getTo().connection.sendMessage(webpackage.getMessage());
	    			System.out.println("Message sent");
	    		} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e){
					e.printStackTrace();
				}
	    	}
		}
	}
