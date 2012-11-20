package org.ques1.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class Main {

	public static void main(String[] args) throws IOException {
		JavaGetUrl HtmlCode = new JavaGetUrl();

		
		String FinalOutput="";
		
			
		String WebsiteHtmlCode= HtmlCode.getWebSiteHtml("http://www.earasoft.com/main");
	        
	    Boolean OnRecord=false;
	    FinalOutput+=ExtractInfo(WebsiteHtmlCode);
			
		
		System.out.println(FinalOutput);	
	}

	public static String ExtractInfo(String strInput) throws IOException{
		String strLine="";
		BufferedReader reader = new BufferedReader(new StringReader(strInput));
		
		while ((strLine = reader.readLine()) != null) {
			if(OnRecordFuncLine(strLine)==true && strLine.trim().length()>=1){
				System.out.println(strLine);
			}

			if(OnRecordPre==true){
				OnRecord=false;
			}
		}
		return strInput;
	}
	
	static Boolean OnRecord=false;
	static Boolean OnRecordPre=false;
	
	public static Boolean OnRecordFuncLine(String StrLine){
		OnRecordPre=false;
		if(StrLine.contains("<tr bgcolor=\"EFEFEF\" valign=top>")){
			OnRecord=true;	
		}
		
		if(StrLine.contains("<tr bgcolor=\"FFFFFF\" valign=top>")){
			OnRecord=true;	
		}
		
		
		if(StrLine.contains("<title>Faculty / Staff Phonebook - Towson University</title>")){
			OnRecord=false;	
		}
		
	
		if(StrLine.contains("</tr>")){
			OnRecordPre=true;	
		}
		
		return OnRecord;
	}
	
}
