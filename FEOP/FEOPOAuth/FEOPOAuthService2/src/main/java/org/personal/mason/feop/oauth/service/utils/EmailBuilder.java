package org.personal.mason.feop.oauth.service.utils;

public class EmailBuilder {

	public static String buildEmailBody(String email, String name) {
		StringBuilder builder = new StringBuilder();
		builder.append("<html><head><style type=\"text/css\">span {display: block;padding: 10px;}label {display: block;font-style: italic;}</style></head><body><h3>Hi, ");
		builder.append(name.isEmpty() ? email : name);
		builder.append("</h3></br><div><span>Welcome to PBM, and wishing you a happy travelling here!</span></div><div><span>Best Regard!</span></div><div><span><label>Master @ PBM</label></span></div></body></html>");
		return builder.toString();
	}
}
