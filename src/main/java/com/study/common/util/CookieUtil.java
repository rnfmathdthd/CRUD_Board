package com.study.common.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	// cookie생성과 조회를 좀 더 간단하게 하는 클래스
	private Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();

	public CookieUtil(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
	} // Cookie객체 생성 하면서 동시에
		// 멤버변수 cookieMap에 request가 갖고있는 cookie 저장

	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}// cookie이름으로 쿠키 조회
		// 있으면 해당 쿠키, 없으면 null 리턴

	public String getValue(String name) throws IOException {
		Cookie cookie = getCookie(name);
		if (cookie == null)
			return null;
		return URLDecoder.decode(cookie.getValue(), "utf-8");
	}// 해당이름의 쿠키가 있으면 그 쿠키의 값 리턴
		// 없으면 null 리턴

	public boolean exists(String name) {
		return cookieMap.get(name) != null;
	}// 해당이름의 쿠키가 있으면 true, 없으면 false

	// 여기까지는 CookieUtil 객체를 생성해야 사용가능한 메소드들

	// 밑에서부턴 CookieUtil 객체 생성 안 해도 되는것들

	public static Cookie 
	createCookie(String name, String value) throws IOException {
		return createCookie(name, value, "", 60 * 60 * 24 * 7);

	}
	
	public static Cookie 
	createCookie(String name, String value,String path) throws IOException {
		return createCookie(name, value, path, -1);
	
	}
	
	public static Cookie 
	createCookie(String name, String value,int maxAge) throws IOException {
		return createCookie(name, value, "", maxAge);
	}

	public static Cookie createCookie(String name, String value, String path, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}

}
