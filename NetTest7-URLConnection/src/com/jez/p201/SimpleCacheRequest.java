package com.jez.p201;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;

public class SimpleCacheRequest extends CacheRequest {
	
	private ByteArrayOutputStream out = new ByteArrayOutputStream();
	
	@Override
	public OutputStream getBody() throws IOException {
		// TODO Auto-generated method stub
		return out;
	}

	@Override
	public void abort() {
		// TODO Auto-generated method stub
		out.reset();
	}
	
	public byte[] getData(){
		if(out.size()==0){
			return null;
		}else{
			return out.toByteArray();
		}
	}

}
