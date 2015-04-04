package com.jez.p112;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SpamCheck {
	//检查ip地址是否是垃圾邮件黑名单
	
	public static final String BLACKHOLE = "sbl.spamhaus.org";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ip = "120.27.40.161";
		if(isSpammer(ip)){
			System.out.println("no");
		}else{
			System.out.println("yes");
		}
	}

	public static boolean isSpammer(String ip){
		try {
			InetAddress address = InetAddress.getByName(ip);
			byte[] quad = address.getAddress();
			String query = BLACKHOLE;
			for(byte octet:quad){
				int unsignedByte = octet<0?octet+256:octet;
				query = unsignedByte+"."+BLACKHOLE;
			}
			InetAddress.getByName(query);
			System.out.println(address);
			return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
}
