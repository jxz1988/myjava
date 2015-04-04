package com.server;

import java.util.concurrent.atomic.AtomicLong;

public class TestAtomic {
	private static final AtomicLong num = new AtomicLong();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		go();
		
	}
	
	public static void go(){
		System.out.println(num.getAndDecrement());
		System.out.println(num.getAndDecrement());
		System.out.println(num.getAndDecrement());
		System.out.println(num.getAndIncrement());
		System.out.println(num.getAndIncrement());
		System.out.println(num.getAndIncrement());
	}
}
