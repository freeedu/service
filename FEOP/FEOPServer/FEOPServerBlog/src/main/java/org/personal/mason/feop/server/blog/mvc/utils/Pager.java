package org.personal.mason.feop.server.blog.mvc.utils;

import javax.servlet.http.HttpServletRequest;

public class Pager {

	private int page;
	private int count;
	private String uri;
	private String queryString;

	public Pager(int page, int count, String uri, String queryString) {
		if (page < 0) {
			page = 0;
		}
		this.page = page + 1;
		this.count = count;
		this.uri = uri;
		if (queryString == null || queryString.isEmpty()) {
			queryString = "p_page=1";
		}
		this.queryString = queryString.replaceFirst("p_page=[\\d]*", "");
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public static Pager getPager(int page, int count, HttpServletRequest request) {
		return new Pager(page, count, request.getRequestURI(), request.getQueryString());
	}
}
