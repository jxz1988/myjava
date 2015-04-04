package com.jez.p201;

import java.util.Date;
import java.util.Locale;

public class CacheControl {
	private Date maxAge = null;
	private Date xMaxAge=null;
	private boolean mustRevalidate = false;
	private boolean noCache=false;
	private boolean noStore=false;
	private boolean proxyRevalidate=false;
	private boolean publicCache=false;
	private boolean privateCache=false;
	
	public CacheControl(String s){
		if(s==null||s.contains(":")){
			return;
		}
		
		String value = s.split(":")[1].trim();
		String[] components=value.split(",");
		
		Date now = new Date();
		for(String component:components){
			try {
				component=component.trim().toLowerCase(Locale.US);
				if(component.startsWith("max-age=")){
					int secondsInTheFuture = Integer.parseInt(component.substring(8));
					maxAge=new Date(now.getTime()+1000*secondsInTheFuture);
				}else if(component.startsWith("s-maxage=")){
					int secondsInTheFuture = Integer.parseInt(component.substring(8));
					xMaxAge = new Date(now.getTime()+1000*secondsInTheFuture);
				}else if(component.equals("must-revalidate")){
					mustRevalidate = true;
				}else if(component.equals("proxy-revalidate")){
					proxyRevalidate = true;
				}else if(component.equals("public")){
					publicCache=true;
				}else if(component.equals("no-cache")){
					noCache=true;
				}else if(component.equals("private")){
					privateCache=true;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public Date getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Date maxAge) {
		this.maxAge = maxAge;
	}

	public Date getxMaxAge() {
		return xMaxAge;
	}

	public void setxMaxAge(Date xMaxAge) {
		this.xMaxAge = xMaxAge;
	}

	public boolean isMustRevalidate() {
		return mustRevalidate;
	}

	public void setMustRevalidate(boolean mustRevalidate) {
		this.mustRevalidate = mustRevalidate;
	}

	public boolean isNoCache() {
		return noCache;
	}

	public void setNoCache(boolean noCache) {
		this.noCache = noCache;
	}

	public boolean isNoStore() {
		return noStore;
	}

	public void setNoStore(boolean noStore) {
		this.noStore = noStore;
	}

	public boolean isProxyRevalidate() {
		return proxyRevalidate;
	}

	public void setProxyRevalidate(boolean proxyRevalidate) {
		this.proxyRevalidate = proxyRevalidate;
	}

	public boolean isPublicCache() {
		return publicCache;
	}

	public void setPublicCache(boolean publicCache) {
		this.publicCache = publicCache;
	}

	public boolean isPrivateCache() {
		return privateCache;
	}

	public void setPrivateCache(boolean privateCache) {
		this.privateCache = privateCache;
	}
	
	
}
