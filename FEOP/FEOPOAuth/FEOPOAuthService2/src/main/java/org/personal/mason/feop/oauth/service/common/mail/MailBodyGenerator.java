package org.personal.mason.feop.oauth.service.common.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class MailBodyGenerator {

	public static String buildEmailBody(String template, Map<String, String> properties) {
		String body = String.copyValueOf(template.toCharArray());
		for (Entry<String, String> entry : properties.entrySet()) {
			body = body.replaceAll("\\$\\{" + entry.getKey() + "[\\s]*\\}", entry.getValue());
		}
		if (validate(body)) {
			return body;
		}
		return null;
	}

	private static boolean validate(String body) {
		return body != null && !body.isEmpty() && !body.contains("\\$\\{");
	}
	
	public static void main(String[] args) {
		String template = "<html><head><title>${title}</title></head><body>${body  }</body></html>";
		Map<String, String> props = new HashMap<String, String>();
		props.put("title", "Hello Test");
		props.put("body", "This is the test Body");
		System.out.println(buildEmailBody(template, props));
		
	}
}
