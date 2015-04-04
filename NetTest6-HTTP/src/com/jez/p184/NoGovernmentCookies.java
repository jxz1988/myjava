package com.jez.p184;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;

public class NoGovernmentCookies implements CookiePolicy {

	@Override
	public boolean shouldAccept(URI uri, HttpCookie cookie) {
		// TODO Auto-generated method stub
		if (uri.getAuthority().toLowerCase().endsWith(".gov")
				|| cookie.getDomain().toLowerCase().endsWith(".gov")) {
			return false;
		}
		CookieManager manager = new CookieManager();
		CookieStore store = manager.getCookieStore();
		return true;
		
	}
}
