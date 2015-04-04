package com.jez.p143;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class TestURI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URI u = new URI("http://www.baidu.com");
			System.out.println(u.getRawFragment());
			URLEncoder.encode("baidu", "UTF-8");
		} catch (URISyntaxException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
