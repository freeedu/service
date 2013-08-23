package org.personal.mason.feop.server.blog.mvc.oauth;

import org.springframework.web.client.RestOperations;


public class FEOPService {

	private RestOperations feopRestTemplate;
	
	public void setFeopRestTemplate(RestOperations feopRestTemplate) {
		this.feopRestTemplate = feopRestTemplate;
	}
	
	
	
}
