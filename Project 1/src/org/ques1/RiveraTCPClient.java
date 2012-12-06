package org.ques1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.ques1.base.GetDataController;
import org.ques1.base.GetFileList;

public class RiveraTCPClient {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		RiveraTCPClient TCPClient1= new RiveraTCPClient();
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		System.out.println("Please pick an option");
		System.out.println("1) Get two file from site (none interactive)");
		System.out.println("2) Get two file from site (interactive)");
		
		
		Integer  intGlobalOption=null;
		boolean isValidHand=false;
		while(isValidHand==false){
			System.out.println("Please pick an option:");
			System.out.println("Example > \"#\"");
			System.out.print("Enter>>");
			
			try{
				String userInputString=br.readLine();
				Integer Option=Integer.parseInt(userInputString);
				
				if( Option==1|| Option==2){
					
					 intGlobalOption=Option;
					isValidHand=true;
				}else{
					System.out.println("Invalid Input");
				}
				
				
				
			}catch (Exception E){
				System.out.println("Invalid Formatting");
			}
			
			
		   
		 }//end while
		
		
		System.out.println( intGlobalOption);
		
		
		
		if(intGlobalOption==1){
			TCPClient1.option1();
		}else{
			TCPClient1.option2();
		}
		//
		
		
		
	}
	
	
	public void option1(){
		//It sends Get requests to a Web server and retrieves the two files f1 and f2. 
		ArrayList<ArrayList<String>> AlFileList= GetFileList.getRequestAndFileList("http://www.earasoft.com/Cosc650/");
		
		ArrayList<String> SelectFiles=  new ArrayList<String>(); 
		int SelectionFileCount=0;
		
		for(int i= 0;i<AlFileList.size();i++){
			if(SelectionFileCount>=2){
				break;
				
			}else{
				String Url=AlFileList.get(i).get(1);
				if(Url.contains(".txt")||Url.contains(".php")){
					//The client selects the server and the files (the user does not choose the server or the files). 
					SelectFiles.add(Url);
					SelectionFileCount++;
				}
				
			}
			
			
	
		}
		
		//System.out.println(SelectFiles);
		
		//The client displays the contents of each file received and the time it took between sending the request and receiving the complete file. 
		//The client should send the requests for f1 and f2 (without waiting till f1 is received). Whichever file retrieval is completed first, it is displayed first. 
		//The contents of the files should not be displayed in an interleaved manner. If f1 is large, f2 may be received first and it will be displayed first.  
		//After receiving and printing a file, the client prints the time that it took to receive the file. 

		GetDataController GetDataControllerObj= new GetDataController();
		
		for(int i=0;i<SelectFiles.size();i++){
			GetDataControllerObj.AddHTTPFileLink(SelectFiles.get(i));
		}
		
		
		GetDataControllerObj.DownloadMutiThread();
		
		
		
	}
	
	public void option2() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		System.out.println("Please enter a website URL");
		System.out.println("Example > \"http://www.earasoft.com/Cosc650/\"");
		
		System.out.print("Enter>>");
		String strWebsiteName=br.readLine();

		
		
		System.out.println("-----\nGetting File List");
		ArrayList<ArrayList<String>> AlFileList= GetFileList.getRequestAndFileList(strWebsiteName);
		
		
		
		for(int i= 0;i<AlFileList.size();i++){
				String FileName=AlFileList.get(i).get(0);
				System.out.println(i+1+") "+FileName);
		}
		
		
		
		
		ArrayList<String> SelectFiles=  new ArrayList<String>(); 
		
		boolean isValidHand=false;
		while(isValidHand==false){
			System.out.println("Please pick two file:");
			System.out.println("Example > \"1,5\"");
			System.out.print("Enter>>");
			
			try{
				String userInputString=br.readLine();
				String[] Files=userInputString.split(",");
				
				if(Files.length==2){
					int intFile1=Integer.parseInt(Files[0]);
					int intFile2=Integer.parseInt(Files[1]);
					
					SelectFiles.add(AlFileList.get(intFile1-1).get(1));
					SelectFiles.add(AlFileList.get(intFile2-1).get(1));
					isValidHand=true;
				}else{
					System.out.println("Invalid Input");
				}
				
				
				
			}catch (Exception E){
				System.out.println("Invalid Formatting");
			}
			
			
		   
		 }//end while
		 
		System.out.println("\n\n\n------------------");
		

		GetDataController GetDataControllerObj= new GetDataController();
		
		for(int i=0;i<SelectFiles.size();i++){
			GetDataControllerObj.AddHTTPFileLink(SelectFiles.get(i));
		}
		
		
		GetDataControllerObj.DownloadMutiThread();
		
		
	}
	

}
