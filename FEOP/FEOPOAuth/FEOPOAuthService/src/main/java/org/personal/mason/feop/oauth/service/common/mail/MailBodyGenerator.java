package org.personal.mason.feop.oauth.service.common.mail;

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
}
