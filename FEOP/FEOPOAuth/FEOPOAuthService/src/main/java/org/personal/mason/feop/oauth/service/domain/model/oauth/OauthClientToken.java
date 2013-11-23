package org.personal.mason.feop.oauth.service.domain.model.oauth;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The persistent class for the oauth_client_token database table.
 * 
 */
@Entity
@Table(name = "oauth_client_token")
public class OauthClientToken extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5124879682905975648L;

	@Column(name = "authentication_id")
	private String authenticationId;

	@Column(name = "client_id")
	private String clientId;

	@Lob
	private byte[] token;

	@Column(name = "token_id")
	private String tokenId;

	@Column(name = "user_name")
	private String userName;

	public OauthClientToken() {
	}

	public String getAuthenticationId() {
		return this.authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public byte[] getToken() {
		return this.token;
	}

	public void setToken(byte[] token) {
		this.token = token;
	}

	public String getTokenId() {
		return this.tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((authenticationId == null) ? 0 : authenticationId.hashCode());
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + Arrays.hashCode(token);
		result = prime * result + ((tokenId == null) ? 0 : tokenId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OauthClientToken other = (OauthClientToken) obj;
		if (authenticationId == null) {
			if (other.authenticationId != null)
				return false;
		} else if (!authenticationId.equals(other.authenticationId))
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (!Arrays.equals(token, other.token))
			return false;
		if (tokenId == null) {
			if (other.tokenId != null)
				return false;
		} else if (!tokenId.equals(other.tokenId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}