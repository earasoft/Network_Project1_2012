package org.ques1.base;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JavaGetUrl {

	public String getWebSiteHtml(String strURL) {
		String Output="";

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
			Output+="\n\n[The size of the web page is "+ builder.length() / 1024 + " Kilobytes.]";

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return Output;
	}



}
