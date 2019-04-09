package server_package;
//ICSD16157 YURIY PYRIH
//DISTRIBUTED SYSTEMS LAB_2



import java.net.*;
import java.io.*;
public class Server {
	 public static void main(String[] args) {
		 try{
			 ServerSocket server = new ServerSocket(5550,50);
			 while (true){
				 System.out.println("Accepting Connection...");
				 System.out.println("Local Address :"+server.getInetAddress()+" Port: "+server.getLocalPort());
				 Socket sock = server.accept();
				 ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
				 ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
				
				 ServerManager manager = new ServerManager(instream, outstream);
				 MessageObject message = (MessageObject) instream.readObject();
				 
				 if (message.getREQUEST() == REQUEST_TYPE.START){ //following the protocol
					 System.out.println("Client -> START");
					 outstream.writeObject(new MessageObject(REQUEST_TYPE.WAITING));
					 System.out.println("Server -> WAITING");
					 
					 do{
						 message = (MessageObject) instream.readObject();
						 
						if(message.getREQUEST() == REQUEST_TYPE.INSERT) {
							System.out.println("Server -> INSERT");
							manager.addObject(message.getObject());
						}
						else if(message.getREQUEST() == REQUEST_TYPE.SEARCH) {
							System.out.println("Server -> SEARCH");
							if( message.getMessage().equals("VIEW_ALL")) {
								outstream.writeObject(new MessageObject(REQUEST_TYPE.RESPOND, manager.getAllList()));
							}
							else {
								outstream.writeObject(new MessageObject(REQUEST_TYPE.RESPOND, manager.getByNameList(message.getMessage())));
							}
						}
						else if(message.getREQUEST() == REQUEST_TYPE.DELETE) {
							System.out.println("Server -> DELETE");
							manager.removeObject(message.getMessage());
						}
						
						 
						  
					 }while(message.getREQUEST() != REQUEST_TYPE.END); //terminate the conversation
					 System.out.println("Client -> END");
				 }
				 else { //not following the protocol
					 System.out.println("Invalid REQUEST");
				 }
				 instream.close();
				 outstream.close();
				 sock.close();
				 System.out.println("Connection Closing...");
			 }
		}
		 catch (Exception ex){
			 System.out.println("Error during I/O");
			 ex.getMessage();
			 ex.printStackTrace();
		 } 
	 }
 }