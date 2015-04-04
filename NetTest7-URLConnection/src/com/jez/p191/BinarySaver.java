package com.jez.p191;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class BinarySaver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//下载文件 图片 js 都可以
		URL u = new URL("http://www.iskyshop.com/resources/images/logo.png");
		URLConnection uc = u.openConnection();
		int contentLength = uc.getContentLength();
		String contentType = uc.getContentType();
		System.out.println(contentType);
		if (contentType.startsWith("text/") || contentLength == -1) {
			throw new IOException();
		}
		InputStream in = uc.getInputStream();
		byte[] data = new byte[contentLength];
		int offset = 0;
		while (offset < contentLength) {
			int bytesRead = in.read(data, offset, data.length - offset);
			if (bytesRead == -1)
				break;
			offset += bytesRead;
		}
		if (offset != contentLength) {
			System.out.println("只读取了" + offset + "byte 总共" + contentLength
					+ "byte");
		}
		String filename=u.getFile();
		System.out.println(filename);
		filename = filename.substring(filename.lastIndexOf("/")+1);
		FileOutputStream out = new FileOutputStream(filename);
		out.write(data);
		out.flush();
		out.close();
	}

}
