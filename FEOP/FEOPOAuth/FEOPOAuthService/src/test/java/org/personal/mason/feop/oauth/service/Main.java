package org.personal.mason.feop.oauth.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Main {
public static void main(String[] args) throws UnsupportedEncodingException {
	System.out.println(URLEncoder.encode("http://localhost:8888/oauth2/", "utf-8"));
	System.out.println(URLEncoder.encode("mason.mei@gmail.com", "utf-8"));
	System.out.println(URLEncoder.encode("Stxaivj1986", "utf-8"));
}
}
