package com.jez.p125;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class TestURL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL u = new URL("http://www.baidu.com");
			System.out.println(u.getProtocol());
			InputStream in = u.openStream();
			in = new BufferedInputStream(in);
			Reader r = new InputStreamReader(in);
			int c;
			while((c=r.read())!=-1){
				System.out.print((char)c);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
