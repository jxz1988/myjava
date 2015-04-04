package com.jez.p187;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.UUID;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		URL u = new URL("http://www.iskyshop.com");
		URLConnection uc = u.openConnection();
		String contentType = uc.getContentType();
		System.out.println(u.getAuthority());
		String encoding="ISO-8859-1";
		int encodingStart = contentType.indexOf("charset=");
		System.out.println(encodingStart);
		if(encodingStart!=-1){
			encoding=contentType.substring(encodingStart+8);
		}
		System.out.println(encoding);
		InputStreamReader reader = new InputStreamReader(
				new BufferedInputStream(uc.getInputStream()));
		int c;
		StringBuffer sb = new StringBuffer();
		while((c=reader.read())!=-1){
			sb.append((char)c);
		}
		Date doucumentSent=new Date(uc.getDate());
		String date = uc.getHeaderField("date");
		System.out.println(date);
		reader.close();
		//System.out.println(sb);
	}
}
