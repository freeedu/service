package org.personal.mason.feop.server.blog.client;

import java.util.Set;

public class HTTPAuthorityProcessor {

	private final Set<AuthorityInterceptor> interceptors;

	public HTTPAuthorityProcessor(Set<AuthorityInterceptor> interceptors) {
		this.interceptors = interceptors;
	}

	public boolean needProcess(String uri) {
		if (interceptors != null) {
			for (AuthorityInterceptor interceptor : interceptors) {
				if (interceptor.needProcess(uri)) {
					return true;
				}
			}
		}
		return false;
	}
}
