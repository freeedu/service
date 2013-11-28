package org.personal.mason.feop.oauth.service.domain.model.oauth;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Arrays;

/**
 * The persistent class for the oauth_refresh_token database table.
 */
@Entity
@Table(name = "oauth_refresh_token")
public class OauthRefreshToken extends AbstractPersistable<Long> {

    private static final long serialVersionUID = -3479808057161868300L;

    @Lob
    private byte[] authentication;

    @Lob
    private byte[] token;

    @Column(name = "token_id")
    private String tokenId;

    public OauthRefreshToken() {
    }

    public byte[] getAuthentication() {
        return this.authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(authentication);
        result = prime * result + Arrays.hashCode(token);
        result = prime * result + ((tokenId == null) ? 0 : tokenId.hashCode());
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
        OauthRefreshToken other = (OauthRefreshToken) obj;
        if (!Arrays.equals(authentication, other.authentication))
            return false;
        if (!Arrays.equals(token, other.token))
            return false;
        if (tokenId == null) {
            if (other.tokenId != null)
                return false;
        } else if (!tokenId.equals(other.tokenId))
            return false;
        return true;
    }

}