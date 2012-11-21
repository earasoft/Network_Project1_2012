package org.ques2;

/**
 Java ECHO server with UDP sockets example
 Silver Moon (m00n.silv3r@gmail.com)
 */

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class UDPServer {
	
	private static HashMap<String,Integer> Sequence= new HashMap<String,Integer>();
	
	
	public static void main(String args[]) throws InterruptedException {
		DatagramSocket sock = null;

		try {
			// 1. creating a server socket, parameter is local port number
			sock = new DatagramSocket(7777);

			// buffer to receive incoming data
			byte[] buffer = new byte[65536];
			DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

			// 2. Wait for an incoming data
			echo("Server socket created. Waiting for incoming data...");

			// communication loop
			while (true) {
				//Getting Data from Client
				sock.receive(incoming);
				byte[] data = incoming.getData();
				MessageObject msgObj = (MessageObject) Serialization.deserializeAndDecompress(data);
				msgObj.setSystemTimeCurrentTime();
				
				if(Sequence.containsKey(msgObj.getClientID())){
					Integer CurrentNum= Sequence.get(msgObj.getClientID());
					Sequence.put(msgObj.getClientID(), CurrentNum+1);
					msgObj.setIntgerSequence(Sequence.get(msgObj.getClientID()));
				}else{
					Sequence.put(msgObj.getClientID(), 2);
					msgObj.setIntgerSequence(Sequence.get(msgObj.getClientID()));
				}
				
				//Inside {  Data  
			

				// echo the details of incoming data - client ip : client port -
				// client message
				echo(incoming.getAddress().getHostAddress() + " : "
						+ incoming.getPort() + " - " + msgObj + "\t ID:"+msgObj.getClientID());


				System.out.println("Before Delay"+msgObj);
				Thread.sleep(100); //Delay
				msgObj.setSystemTimeCurrentTime();
				System.out.println("After Delay"+msgObj);
				
				//Inside }
				//Required Post 
				msgObj.ChangeStateToAck();
				
				
				if(Sequence.containsKey(msgObj.getClientID())){
					Integer CurrentNum= Sequence.get(msgObj.getClientID());
					Sequence.put(msgObj.getClientID(), CurrentNum+1);
					msgObj.setIntgerSequence(Sequence.get(msgObj.getClientID()));
				}else{
					Sequence.put(msgObj.getClientID(), 2);
					msgObj.setIntgerSequence(Sequence.get(msgObj.getClientID()));
				}
				
				//Sending Data back to Client
				byte[] MsgObjData = Serialization.serializeAndCompress(msgObj);
				DatagramPacket dp = new DatagramPacket(MsgObjData,
						MsgObjData.length, incoming.getAddress(),
						incoming.getPort());
				sock.send(dp);
			}
		}

		catch (IOException e) {
			System.err.println("IOException " + e);
		}
	}

	// simple function to echo data to terminal
	public static void echo(String msg) {
		System.out.println(msg);
	}
}
