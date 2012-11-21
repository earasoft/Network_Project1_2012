package org.ques1.base;

import java.util.ArrayList;

public class GetDataController {

	private ArrayList<GetDataWebSite> Threads= new ArrayList<GetDataWebSite>();
	
	//used for MultiThreading
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		GetDataController GetDataControllerObj= new GetDataController();
		GetDataControllerObj.AddHTTPFileLink("http://www.earasoft.com/main/warning.php");
		GetDataControllerObj.AddHTTPFileLink("http://www.earasoft.com/main/Hash.txt");
		
		
		
		//GetDataControllerObj.DownloadSingleThread();
		GetDataControllerObj.DownloadMutiThread();
		

		
	}

	public void AddHTTPFileLink(String Input){
		Threads.add(new GetDataWebSite(Input));
	}
	
	public void DownloadSingleThread(){
		for(int i=0;i<Threads.size();i++){
			
			Threads.get(i).runSingle();
			
		}
	}
	
	public void DownloadMutiThread(){
		for(int i=0;i<Threads.size();i++){
			
			Threads.get(i).start();
			
		}
	}
	
	
}
