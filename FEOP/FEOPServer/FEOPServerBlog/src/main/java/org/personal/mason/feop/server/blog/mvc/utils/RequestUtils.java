package org.personal.mason.feop.server.blog.mvc.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static String getPaginationRequestFormat(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        String queryString = request.getQueryString();

        if (queryString != null) {
            queryString.replaceFirst("p=[\\d]*", "p=%d");
            return String.format("%s?%s", requestUrl, queryString);
        }
        return requestUrl;
    }
}
