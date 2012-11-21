package org.ques1;

import java.util.ArrayList;

import org.ques1.base.GetDataController;
import org.ques1.base.GetFileList;

public class TCPClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TCPClient TCPClient1= new TCPClient();
		TCPClient1.option1();
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
	
	public void option2(){
		
	}
	

}
