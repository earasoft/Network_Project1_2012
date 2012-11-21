package org.ques2;

/**
 Java ECHO client with UDP sockets example
 Silver Moon (m00n.silv3r@gmail.com)
 */

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Random;

public class UDPClient {	 
	static String ClientID;
	
	public static void main(String args[]) {
		ClientID=org.apache.commons.codec.digest.DigestUtils.md2Hex(new BigInteger(256, new Random()).toString());
		DatagramSocket sock = null;
		int port = 7777;
		String s;

		BufferedReader cin = new BufferedReader(
				new InputStreamReader(System.in));

		try {
			sock = new DatagramSocket();
			sock.setSoTimeout(50);   // set the timeout in millisecounds.

			InetAddress host = InetAddress.getByName("localhost");

			while (true) {
				MessageObject ClientMsgObj= new MessageObject();
				ClientMsgObj.setClientID(ClientID);
				ClientMsgObj.setSystemTimeCurrentTime();
				
				if(ClientMsgObj.getIntgerSequence()==null)
					ClientMsgObj.setIntgerSequence(100);
				//Inside Obj
				
				// take input and send the packet
				echo("Enter message to send : ");
				s = (String) cin.readLine();
				
				ClientMsgObj.setOneLineMessage(s);
				
				

				

				byte[] MsgObjDataSending = Serialization.serializeAndCompress(ClientMsgObj);
				DatagramPacket dp = new DatagramPacket(MsgObjDataSending,MsgObjDataSending.length, host, port);
				sock.send(dp);
				
				
				
				/////////////////////////////////////////////////////////////////////////
				// now receive reply
				// buffer to receive incoming data
				
				int maxtries=0;
				boolean retry = true;
				while(retry==true){
					retry=false;
					try {
						
				
						byte[] buffer = new byte[65536];
						DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
						sock.receive(reply);

						
						byte[] data = reply.getData();
						MessageObject msgObj = (MessageObject) Serialization.deserializeAndDecompress(data);
						ClientMsgObj.setSystemTimeCurrentTime();
						//Inside OBJ
						

						// echo the details of incoming data - client ip : client port -
						// client message
						echo(reply.getAddress().getHostAddress() + " : "
								+ reply.getPort() + " - " + msgObj);
								
						
						
					}catch (Exception e) {
						retry=true;
						maxtries++;
						
						sock.setSoTimeout(sock.getSoTimeout()*2);  // set the timeout in millisecounds.
						System.err.println("Retry #"+ maxtries + "   " + e+"  Timeout="+sock.getSoTimeout());
						
						
						
						if(maxtries>=3){
							retry=false;
						}
					}
				}
			
		
						
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
