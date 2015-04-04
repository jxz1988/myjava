package com.jez.p97;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class TestInetAddress {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InetAddress address = InetAddress.getByName("localhost");
		InetAddress address2 = InetAddress.getByName("c2ctest.iskyshop.com");
		System.out.println(address.equals(address2));
		//InetAddress address2 = InetAddress.getByName("42.96.140.145");
		//InetAddress[] addresses = InetAddress.getAllByName("c2ctest.iskyshop.com");
		//for(InetAddress add:addresses){
		//	System.out.println(add);
		//}
		//InetAddress me = InetAddress.getLocalHost();
		//System.out.println(me.isReachable(2000));
		NetworkInterface interface1 = NetworkInterface.getByInetAddress(address);
		System.out.println(interface1);
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while(interfaces.hasMoreElements()){
			NetworkInterface ni = interfaces.nextElement();
			System.out.println(ni);
		}
	}

}
