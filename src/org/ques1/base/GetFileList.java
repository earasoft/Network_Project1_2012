package org.ques1.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetFileList {

	public static void main(String[] args){
		
		
		//MergeLinks("https://github.com/earasoft/Network_Project1_2012/","/earasoft/Network_Project1_2012/file.zip");
		ArrayList<ArrayList<String>> AlFileList= getRequestAndFileList("http://www.earasoft.com/main/");
		
		for(int i= 0;i<AlFileList.size();i++){
			System.out.println(AlFileList.get(i));
		}
	}
	
	public static String MergeLinks(String Left,String Right){
		
		Left=Left.substring(Left.length()-1).equals("/")?Left.substring(0,Left.length()-1):Left;
		Right=Right.substring(0,1).equals("/")?Right.substring(1,Right.length()):Right;
		
		String delims = "//|/";
		String[] tokensLeft = Left.split(delims);
		
		//System.out.println(Arrays.toString(tokensLeft));
		
		String[] tokensRight = Right.split(delims);
		
		//System.out.println(Arrays.toString(tokensRight));
		
		HashSet<String> HashSetObj= new HashSet<String>();
		
		ArrayList<String> PreOutput= new ArrayList<String>();
		
		for(int i=0;i<tokensLeft.length;i++){
			HashSetObj.add(tokensLeft[i]);
			PreOutput.add(tokensLeft[i]);
		}
		
		
		for(int i=0;i<tokensRight.length;i++){
			
			if(HashSetObj.contains(tokensRight[i])){
				//exist
			}else{
				PreOutput.add(tokensRight[i]);
			}
			
		}
		
		
		
		StringBuilder Output= new StringBuilder();
		for(int i=0;i<PreOutput.size();i++){
			if(i==0){
				Output.append(PreOutput.get(i)+"//");
			}else if(i==PreOutput.size()-1){
				Output.append(PreOutput.get(i)+"");
			}else{
				Output.append(PreOutput.get(i)+"/");
			}
		}
		
		
		return Output.toString();
	}

	
	
	public static ArrayList<ArrayList<String>> getRequestAndFileList(String strWebpage){
		ArrayList<ArrayList<String>> AlFileList=new ArrayList<ArrayList<String>>();
		
		
		//This Object is used to send a Get Request to the site to retrieve contents of page
		GetDataWebSite HtmlCode = new GetDataWebSite();
		//Using JavaGetURL to send get request and get contents. Store content in String Variable
		String WebsiteHtmlCode=HtmlCode.getWebSiteHtml(strWebpage);
	    
		
	    //System.out.println(WebsiteHtmlCode);  //Debug-Print out content
		//System.out.println("-----------------\n\n\n"); //Debug-
		
		//Object used to parse html code
	    Document Test=Jsoup.parse(WebsiteHtmlCode);
		
	    Elements EleLinks= Test.select("a[href]");
	    
	    //System.out.println("Number of Links=" + EleLinks.size());
	    for(int i=0;i<EleLinks.size();i++){
	    	String linkHref = EleLinks.get(i).attr("href"); // "http://example.com/"
	    	String full; //full
	    	
	    	
	    	if(detectFiles(linkHref)){
	    		//System.out.println(linkHref);
	    		if(detectHTTPProtocols(linkHref)){
	    			full=linkHref;
	    		}else{
	    			
	    			
	    			full=MergeLinks(strWebpage,linkHref);
	    			
	    		}
	    		
	    		ArrayList<String> AlInner=new ArrayList<String>();
	    		String FileName=linkHref.lastIndexOf("/")>=1?linkHref.substring(linkHref.lastIndexOf("/")+1,linkHref.length()):linkHref;
	    		
	    		try {
					AlInner.add(java.net.URLDecoder.decode( FileName, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		AlInner.add(full);
	    		AlInner.add("Debug="+linkHref);
	    		
	    		
	    		if(detectHTTPWebpage(full)){
	    			AlFileList.add(AlInner);
	    		}
	    		
	    		
	    	}
	    }//end loop
	    return AlFileList;
	}//end sub
	
	public static boolean detectHTTPProtocols(String StrInput){
		Pattern pattern = Pattern.compile("^(http://)|(https://)|(ftp://)|(git://)");
		Matcher matcher = pattern.matcher(StrInput);
		if (matcher.find()) {
		    //System.out.println(matcher.group(0)); //prints /{item}/
		    return true;
		} else {
		    //System.out.println("Match not found");
		    return false;
		}
	}
	
	public static boolean detectHTTPWebpage(String StrInput){
		Pattern pattern = Pattern.compile("^(http://)|(https://)");
		Matcher matcher = pattern.matcher(StrInput);
		if (matcher.find()) {
		    //System.out.println(matcher.group(0)); //prints /{item}/
		    return true;
		} else {
		    //System.out.println("Match not found");
		    return false;
		}
	}
	
	
	public static boolean detectFilesIfLink(String StrInput){
		Pattern pattern = Pattern.compile("^(http|https|ftp)\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(:[a-zA-Z0-9]*)?/?");
		Matcher matcher = pattern.matcher(StrInput);
		if (matcher.find()) {
		    //System.out.println(matcher.group(0)); //prints /{item}/
		    
		    if(StrInput.length()==matcher.group(0).length()){
		    	 return true;
		    }else{
		    	return false;
		    }
		   
		} else {
		    //System.out.println("Match not found");
		    return false;
		}
	}
	
	
	public static boolean detectFilesEmail(String StrInput){
		Pattern pattern = Pattern.compile("(@)+([a-zA-Z0-9]+.([a-zA-Z0-9]{3}))");
		Matcher matcher = pattern.matcher(StrInput);
		if (matcher.find()) {
		    //System.out.println(matcher.group(0)); //prints /{item}/
			return true;
		} else {
		    //System.out.println("Match not found");
		    return false;
		}
	}
	
	public static boolean detectFiles(String StrInput){
		Pattern pattern = Pattern.compile("([^\\s]+(\\.(?i)([a-zA-Z0-9]{1,3}))$)");
		Matcher matcher = pattern.matcher(StrInput);
		if (matcher.find()) {
		    //System.out.println(matcher.group(0)); //prints /{item}/
			if(detectFilesIfLink(StrInput)||detectFilesEmail(StrInput)){
				return false;
			}else{
				 return true;
			}
		   
		} else {
		    //System.out.println("Match not found");
		    return false;
		}
	}
	
}
