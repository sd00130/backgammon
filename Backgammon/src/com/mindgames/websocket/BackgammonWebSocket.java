package com.mindgames.websocket;

import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import org.eclipse.jetty.websocket.WebSocket;

import com.google.gson.Gson;
import com.mindgames.message.ChatMessage;
import com.mindgames.message.JoinMessage;
import com.mindgames.message.Message;
import com.mindgames.message.MoveMessage;
import com.mindgames.message.TypeMessage;


public class BackgammonWebSocket implements WebSocket.OnTextMessage {

	private final Set<BackgammonWebSocket> members;
	public static Gson gson = new Gson();
    Connection connection;
    
	public BackgammonWebSocket(Set<BackgammonWebSocket> members) {
		super();
		this.members = members;
	}

	 public void onOpen(Connection connection)
     {
		 this.connection = connection;
		 members.add(this);
		 System.out.println("onOpen called");
		 System.out.println(members);
     }
	
	 
    public void onClose(int code, String message){
        // Log.info(this+" onDisconnect");
        members.remove(this);
    }
	
	 public void onMessage(String data){
			System.out.println("onMessage called");
	        if (data.indexOf("disconnect")>=0)
	            connection.close();
	        else
	        {
	        	 try
	             {
	        		 Message message = null;
	        		 TypeMessage typeMessage = gson.fromJson(data, TypeMessage.class);
	        		 if (typeMessage.type.equals("MoveMessage")){
	        			 message = gson.fromJson(data, MoveMessage.class);
	        		 }
	        		 if (typeMessage.type.equals("ChatMessage")){
	        			message = gson.fromJson(data, ChatMessage.class);
	        		 }
	        		 if (typeMessage.type.equals("JoinMessage")){
		        			message = gson.fromJson(data, JoinMessage.class);
	        		 }
	        		 message.process();
	             }
	        	 catch (InterruptedException e) 
	             {
	        		 e.printStackTrace();
	             } 
//	            for (BackgammonWebSocket member : members)
//	            {
//	                try
//	                {
//	                	send(new Webpackage(data, member));
//	                	System.out.println("Message put");
//	                }
//	                catch (InterruptedException e) 
//	                {
//						e.printStackTrace();
//					} 
//	            }
	        }
     }
	 
     public void onMessage(byte frame, byte[] data,int offset, int length){
         // Log.info(this+" onMessage: "+TypeUtil.toHexString(data,offset,length));
     }

	/**
	 * puts message in all nodes queues
	 * @param message to put
	 * @throws InterruptedException
	 */
	public void send(Webpackage message) throws InterruptedException{
		for (LinkedBlockingQueue<Webpackage> linkedBlockingQueue : BackgammonWebSocketServlet.queues) {
			linkedBlockingQueue.put(message);
		}
	}
	
}
