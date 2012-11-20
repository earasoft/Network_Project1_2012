package org.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientBase {

    static String server="127.0.0.1";
    final static int servPort = 12011;
    private static Socket echoSocket = null;
    private static PrintWriter out = null;
    private static BufferedReader in = null;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createSocket();
			
		System.out.println(TCP_Command("InputText","ALL UPPER CASE"));
		
	}
	
	public static boolean createSocket() {
		boolean blnConnected=false;
		try {
			blnConnected=true;
            echoSocket = new Socket(server, servPort);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + server);
            //System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: " + server);
            
        }
		
		return blnConnected;
	}
	
	
	public static String TCP_Command(String Input,String StrStatus){
     	String userInput=Input;
    	String Output="";
        try {
       
        		String Command="";
        		
        	    if(StrStatus.equalsIgnoreCase("ALL UPPER CASE")){
        	    	Command="UC|";
        	    }
        	    else if(StrStatus.equalsIgnoreCase("ALL LOWER CASE")){
        	    	Command="LC|";
        	    }        	    
        	    else if(StrStatus.equalsIgnoreCase("STRING TO HEX")){
        	    	Command="SH|";
        	    }
        	    else if(StrStatus.equalsIgnoreCase("HEX TO STRING")){
        	    	Command="HS|";
        	    }
        	    else if(StrStatus.equalsIgnoreCase("STRING TO ASCII")){
        	    	Command="SA|";
        	    }
        	    else if(StrStatus.equalsIgnoreCase("ASCII TO STRING")){
        	    	Command="AS|";
        	    }
        	    else if(StrStatus.equalsIgnoreCase("NO CHANGE")){
        	    	Command="NO|";
        	    }
        	    else{
        	    	Command="??|";
        	    }
        
                out.println(org.shared.EncodeWeak.String2Hex(Command+userInput));//Out to Server
              
                Output=org.shared.EncodeWeak.Hex2String(in.readLine());//Back from Server
        } catch (IOException ex) {
        	
            System.out.println(ex);
           
        }
        
		return Output;	
    }


}
