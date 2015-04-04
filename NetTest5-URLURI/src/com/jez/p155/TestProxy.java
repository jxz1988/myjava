package com.jez.p155;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;

public class TestProxy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SocketAddress address = new InetSocketAddress("demo.com",80);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
		ProxySelector selector = new LocalProxySelector();
		ProxySelector.setDefault(selector);
		
	}

}
