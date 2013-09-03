package org.personal.mason.feop.server.blog.mvc.utils;

import java.util.ArrayList;
import java.util.List;

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
		this.queryString = reconstructQueryString(queryString);
	}

	private String reconstructQueryString(String queryString) {
		if (queryString == null || queryString.isEmpty()) {
			return "";
		}

		String[] pvs = queryString.split("&");
		List<String> pvlist = new ArrayList<>();
		if (pvs != null) {
			for (String pv : pvs) {
				if (pv.matches("p_page=[\\d]*")) {
					continue;
				}
				pvlist.add(pv);
			}
		}

		StringBuilder builder = new StringBuilder();
		for (String pv : pvlist) {
			builder.append("&").append(pv);
		}
		return builder.toString();
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
