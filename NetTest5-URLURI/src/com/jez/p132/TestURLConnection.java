package com.jez.p132;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

public class TestURLConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL u = new URL("http://www.oreilly.com");
			URLConnection url = u.openConnection();
			InputStream in = url.getInputStream();
			in = new BufferedInputStream(in);
			Reader read = new InputStreamReader(in);
			int c;
			while((c=read.read())!=-1){
				System.out.print((char)c);
			}
			System.out.println(u.getFile());
			System.out.println(u.getHost());
			System.out.println(u.getPort());
			System.out.println(u.getProtocol());
			System.out.println(u.getRef());
			System.out.println(u.getQuery());
			System.out.println(u.getPath());
			System.out.println(u.getUserInfo());
			System.out.println(u.getAuthority());
			Object obj = u.getContent();
			System.out.println(obj.getClass().getName());
			Class<?>[] types = new Class[3];
			types[0]=String.class;
			types[1]=Reader.class;
			types[2]=InputStream.class;
			Object o = u.getContent(types);
			if(o instanceof String){
				System.out.println("String");
			}else if(o instanceof Reader){
				System.out.println("Reader");
			}else if(o instanceof InputStream){
				System.out.println("InputStream");
			}else{
				System.out.println("error");
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
