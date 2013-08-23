package org.personal.mason.feop.server.blog.client.oauth.implicit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.codehaus.jackson.map.ObjectMapper;
import org.personal.mason.feop.server.blog.client.oauth.OAuthLoginInfoProvider;
import org.personal.mason.feop.server.blog.client.oauth.code.AuthorizationCodeAuthentication;

public class ImplicitLoginInfoProvider extends OAuthLoginInfoProvider {

	@Override
	public String getAuthorizationRequestUrl(String callback) {
		throw new UnsupportedOperationException("Not support this kind operation in implicit login.");
	}

	@Override
	public void processAccessToken(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (!isFromOServer(request)) {
				String uri = getAccessTokenRequestUrl(request.getRequestURL().toString());
				response.sendRedirect(uri);
			} else{
				request.getAttributeNames();
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getAccessTokenRequestUrl(String callback) {
		StringBuilder urlPattern = new StringBuilder();
		List<String> params = new LinkedList<>();
		urlPattern.append("%s?");
		params.add(getConfiguration().getAuthUrl());

		urlPattern.append("client_id=%s");
		params.add(getConfiguration().getClientId());
		urlPattern.append("&client_secret=%s");
		params.add(getConfiguration().getClientSecret());

		urlPattern.append("&response_type=token");

		if (callback != null) {
			try {
				params.add(URLEncoder.encode(callback, "UTF-8"));
				urlPattern.append("&redirect_uri=%s");
			} catch (UnsupportedEncodingException e) {
			}
		}

		if (getConfiguration().getScope() != null) {
			urlPattern.append("&scope=%s");
			params.add(getConfiguration().getScope());
		}

		// if(getConfiguration().isEnableCSRF()){
		// urlPattern.append("&state=%s");
		// //TODO:
		// }

		return String.format(urlPattern.toString(), params.toArray());
	}

	@Override
	public boolean isDirectlyRequestToken(HttpServletRequest request) {
		return true;
	}

	public boolean isFromOServer(HttpServletRequest request) {
		String referer = request.getHeader("referer");
		return referer != null && referer.contains(getConfiguration().getAuthUrl());
	}

}
