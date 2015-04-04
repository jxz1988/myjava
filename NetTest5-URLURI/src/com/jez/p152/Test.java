package com.jez.p152;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueryString query = new QueryString();
		query.add("wd", "java");
		String url = "http://www.baidu.com/s?" + query;
		System.out.println(url);
		try {
			URL u = new URL(url);
			try (InputStream in = new BufferedInputStream(u.openStream());) {
				InputStreamReader theHTML = new InputStreamReader(in);
				StringBuffer out = new StringBuffer();
				int c;
				while((c=theHTML.read())!=-1){
					out.append((char)c);
				}
				System.out.println(out);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
