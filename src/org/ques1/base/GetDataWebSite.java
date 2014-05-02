package org.ques1.base;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.shared.performance.Timing;

public class GetDataWebSite extends Thread {

	private String strDownloadURL;
	
	public String getStrDownloadURL() {
		return strDownloadURL;
	}

	//used for Mutli-Threading(non-Javadoc)
	public GetDataWebSite(String strDownloadURL) {
		super();
		this.strDownloadURL = strDownloadURL;
	}

	public GetDataWebSite() {
		// TODO Auto-generated constructor stub
	}

	public String getWebSiteHtml(String strURL) {
		String Output="";

		Timing Time=new Timing();
		Time.start();
		
		try {
			URL url = new URL(strURL);
			URLConnection urlc = url.openConnection();

			BufferedInputStream buffer = new BufferedInputStream(
					urlc.getInputStream());

			StringBuilder builder = new StringBuilder();
			int byteRead;
			while ((byteRead = buffer.read()) != -1)
				builder.append((char) byteRead);

			buffer.close();

			
			Output+=builder.toString().trim();
			Output+="\n\n[The URL ="+strURL+".  The size of the web page is "+ builder.length() / 1024 + " Kilobytes. It Took "+Time.stop_SecDouble()+" Seconds]";

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return Output;
	}
	
	
	
	public void runSingle() {
		System.out.println(this.getWebSiteHtml(strDownloadURL));

	}

	/**
	 * For Mutli-Threading(non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		System.out.println(this.getWebSiteHtml(strDownloadURL));		
	}
}
