package com.redis;

import redis.clients.jedis.Jedis;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis jedis = new Jedis("127.0.0.1",6379);  
	    String value = jedis.get("foo");  
	    System.out.println(value);
	    String v2 = jedis.get("aaa");
	    System.out.println(v2);
	    jedis.del("aaa");
	}

}
