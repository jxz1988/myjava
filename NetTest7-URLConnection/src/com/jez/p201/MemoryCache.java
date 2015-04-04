package com.jez.p201;

import java.io.IOException;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryCache extends ResponseCache {

	private final Map<URI, SimpleCacheResponse> responses = new ConcurrentHashMap<URI, SimpleCacheResponse>();
	private final int maxEntries;

	public MemoryCache() {
		// TODO Auto-generated constructor stub
		this(100);
	}

	public MemoryCache(int maxEntries) {
		this.maxEntries = maxEntries;
	}

	@Override
	public CacheResponse get(URI uri, String rqstMethod,
			Map<String, List<String>> rqstHeaders) throws IOException {
		// TODO Auto-generated method stub
		if ("GET".equals(rqstMethod)) {
			SimpleCacheResponse response = responses.get(uri);
			if (response != null && response.isExpired()) {
				responses.remove(response);
				response = null;
			}
			return response;
		} else {
			return null;
		}

	}

	@Override
	public CacheRequest put(URI uri, URLConnection conn) throws IOException {
		// TODO Auto-generated method stub
		if (responses.size() >= maxEntries) {
			return null;
		}
		CacheControl control = new CacheControl(
				conn.getHeaderField("Cache-Control"));
		if(control.isNoStore()){
			return null;
		}else if(!conn.getHeaderField(0).startsWith("GET")){
			return null;
		}
		SimpleCacheRequest request = new SimpleCacheRequest();
		SimpleCacheResponse response = new SimpleCacheResponse(request, conn, control);
		responses.put(uri, response);
		return request;
	}

}
