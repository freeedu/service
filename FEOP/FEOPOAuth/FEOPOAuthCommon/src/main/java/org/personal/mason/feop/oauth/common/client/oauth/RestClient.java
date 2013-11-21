package org.personal.mason.feop.oauth.common.client.oauth;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;


public class RestClient {
	private static RestClient restClient;

	private Client client;

	public synchronized static RestClient getInstance() {
		if (restClient == null) {
			restClient = new RestClient();
		}
		return restClient;
	}

	private RestClient() {
		ClientConfig config = new DefaultClientConfig();
		config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		client = Client.create(config);
	}

	public Client getClient() {
		return client;
	}

	public WebResource getResource(String resourceUri) {
		return client.resource(resourceUri);
	}
}
