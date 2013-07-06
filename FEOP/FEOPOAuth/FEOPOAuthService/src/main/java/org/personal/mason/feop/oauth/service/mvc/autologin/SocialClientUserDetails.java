package org.personal.mason.feop.oauth.service.mvc.autologin;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class SocialClientUserDetails extends AbstractAuthenticationToken {

	private static final long serialVersionUID = -3276533379158611377L;
	private String username;
	private String email;
	private String name;
	private Object id;
	private String source;

	public SocialClientUserDetails(String username, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		setAuthenticated(authorities != null && !authorities.isEmpty());
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public Object getExternalId() {
		return id;
	}

	public void setExternalId(Object id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		// This is used as the principal name (which could then be used to look
		// up tokens etc)
		return username;
	}

	public void setFullName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return this.name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public Object getCredentials() {
		return "N/A";
	}

	@Override
	public Object getPrincipal() {
		return this.username;
	}

	public static class Source {

		public static String CLOUD_FOUNDRY = "cloudfoundry";

		public static String GITHUB = "github";

		public static String FACEBOOK = "facebook";

		public static String TWITTER = "twitter";

		public static String LINKEDIN = "linkedin";

		public static String GOOGLE = "google";

		public static String classify(String userInfoUrl) {
			String key = userInfoUrl.toLowerCase().replaceAll(".*//([a-z.]*)/.*", "$1");
			if (userInfoUrl.contains("cloudfoundry.com")) {
				key = CLOUD_FOUNDRY;
			} else if (userInfoUrl.contains("google.com") || userInfoUrl.contains("googleapis.com")) {
				key = GOOGLE;
			} else if (userInfoUrl.contains("github.com")) {
				key = GITHUB;
			} else if (userInfoUrl.contains("twitter.com")) {
				key = TWITTER;
			} else if (userInfoUrl.contains("linkedin.com")) {
				key = LINKEDIN;
			} else {
				String[] keys = key.split("\\.");
				if (keys.length > 1) {
					key = keys[keys.length - 2];
				}
				if ("co".equals(key) && keys.length > 2) {
					key = keys[keys.length - 3];
				}
			}
			return key;
		}
	}

}
