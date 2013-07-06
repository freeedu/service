package org.personal.mason.feop.oauth.service.domain;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The persistent class for the oauth_code database table.
 * 
 */
@Entity
@Table(name = "oauth_code")
public class OauthCode extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 880869016537752129L;

	@Lob
	private byte[] authentication;

	private String code;

	public OauthCode() {
	}

	public byte[] getAuthentication() {
		return this.authentication;
	}

	public void setAuthentication(byte[] authentication) {
		this.authentication = authentication;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(authentication);
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		OauthCode other = (OauthCode) obj;
		if (!Arrays.equals(authentication, other.authentication))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

}