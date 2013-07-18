package org.personal.mason.feop.server.blog.mvc.utils;


public class CollectionUtils {

	private static final String SPLITER = ",";

	public static boolean collectionContains(String collectionString, String content) {
		String[] elements = collectionString.split(SPLITER);
		for (String element : elements) {
			if (element.equals(content)) {
				return true;
			}
		}

		return false;
	}

	public static String removeFromCollection(String collectionString, String content) {
		String[] elements = collectionString.split(SPLITER);
		StringBuilder builder = new StringBuilder();
		for (String element : elements) {
			if (element.equals(content)) {
				continue;
			}
			builder.append(element).append(",");
		}
		if (builder.length() > 0) {
			builder.deleteCharAt(builder.lastIndexOf(","));
		}
		return builder.toString();
	}
}
