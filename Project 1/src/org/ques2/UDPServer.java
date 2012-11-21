package org.ques2;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class UDPServer {
	
	public static void main(String args[]) throws InterruptedException {
		UDPServer UDPServerObj= new UDPServer();

		UDPServerObj.startServer();
	}
	
	
	private HashMap<String,Integer> Sequence= new HashMap<String,Integer>();
	private DatagramSocket sock = null;
	private int port = 7777;
	
	public UDPServer(){
		// 1. creating a server socket, parameter is local port number
		try {
			sock = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HashMap<String,Object> recieveObject() throws IOException{
		// buffer to receive incoming data
		byte[] buffer = new byte[65536];
		DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
		
		sock.receive(incoming);
		System.out.println("ClientReceive");
		byte[] data = incoming.getData();
		
		HashMap<String,Object> Objs= new HashMap<String,Object>();
		Objs.put("MessageObject", (MessageObject) Serialization.deserializeAndDecompress(data));
		Objs.put("Connection", (DatagramPacket)incoming);
		
		return Objs;
	}
	
	
	public void sendObject(MessageObject msgObj,DatagramPacket outgoing) throws IOException{
		//Sending Data back to Client
		byte[] MsgObjData = Serialization.serializeAndCompress(msgObj);
		DatagramPacket dp = new DatagramPacket(MsgObjData,
				MsgObjData.length, outgoing.getAddress(),
				outgoing.getPort());
		sock.send(dp);
	}
	
	public void addSeqNum(MessageObject msgObj){
		if(Sequence.containsKey(msgObj.getClientID())){
			Integer CurrentNum= Sequence.get(msgObj.getClientID());
			Sequence.put(msgObj.getClientID(), CurrentNum+1);
			msgObj.setIntgerSequence(Sequence.get(msgObj.getClientID()));
		}else{
			Sequence.put(msgObj.getClientID(), 2);
			msgObj.setIntgerSequence(Sequence.get(msgObj.getClientID()));
		}
	}
	
	public void startServer() throws InterruptedException{
		try {
			echo("Server socket created. Waiting for incoming data...");

			// communication loop
			while (true) {
				//Getting Data from Client
				HashMap<String,Object> Objs= recieveObject();
				DatagramPacket incoming=(DatagramPacket) Objs.get("Connection");
				MessageObject msgObj = (MessageObject) Objs.get("MessageObject");
				
				
				//Pre-Requirement 
				msgObj.setSystemTimeCurrentTime();
				addSeqNum(msgObj);
				//Inside Object {  Data  
				//--------------------
			

				// echo the details of incoming data - client ip : client port -
				// client message
				echo(incoming.getAddress().getHostAddress() + " : "
						+ incoming.getPort() + " - " + msgObj + "\t ID:"+msgObj.getClientID());


				//System.out.println("Before Delay"+msgObj);
				Thread.sleep(100); //Delay
				//msgObj.setSystemTimeCurrentTime();
				//System.out.println("After Delay"+msgObj);
				
				
				//--------------------
				//Inside Object }
				//Post Requirement 
				msgObj.ChangeStateToAck();
				addSeqNum(msgObj);
				//sending Object
				sendObject(msgObj,incoming);
				
			}//End Loop
		}catch (IOException e) {
			System.err.println("IOException " + e);
		}
	}
	// simple function to echo data to terminal
	public static void echo(String msg) {
		System.out.println(msg);
	}
}
