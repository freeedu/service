package org.personal.mason.feop.oauth.service.domain.model.oauth;

import org.personal.mason.feop.oauth.common.domain.model.FOEPPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the oauth_client_details database table.
 */
@Entity
@Table(name = "oauth_client_details")
public class OauthClientDetail extends FOEPPersistable<Long> {

    private static final long serialVersionUID = -668331777493158496L;

    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    @Column(name = "additional_information")
    private String additionalInformation;

    private String authorities;

    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    @Column(name = "client_id", unique = true)
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    @Column(name = "resource_ids")
    private String resourceIds;

    private String scope;

    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;

    @Column(name = "owner")
    private String owner;

    public OauthClientDetail() {
    }

    public Integer getAccessTokenValidity() {
        return this.accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public String getAdditionalInformation() {
        return this.additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Integer getRefreshTokenValidity() {
        return this.refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getResourceIds() {
        return this.resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getWebServerRedirectUri() {
        return this.webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((accessTokenValidity == null) ? 0 : accessTokenValidity.hashCode());
        result = prime * result + ((additionalInformation == null) ? 0 : additionalInformation.hashCode());
        result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
        result = prime * result + ((authorizedGrantTypes == null) ? 0 : authorizedGrantTypes.hashCode());
        result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
        result = prime * result + ((clientSecret == null) ? 0 : clientSecret.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((refreshTokenValidity == null) ? 0 : refreshTokenValidity.hashCode());
        result = prime * result + ((resourceIds == null) ? 0 : resourceIds.hashCode());
        result = prime * result + ((scope == null) ? 0 : scope.hashCode());
        result = prime * result + ((webServerRedirectUri == null) ? 0 : webServerRedirectUri.hashCode());
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
        OauthClientDetail other = (OauthClientDetail) obj;
        if (accessTokenValidity == null) {
            if (other.accessTokenValidity != null)
                return false;
        } else if (!accessTokenValidity.equals(other.accessTokenValidity))
            return false;
        if (additionalInformation == null) {
            if (other.additionalInformation != null)
                return false;
        } else if (!additionalInformation.equals(other.additionalInformation))
            return false;
        if (authorities == null) {
            if (other.authorities != null)
                return false;
        } else if (!authorities.equals(other.authorities))
            return false;
        if (authorizedGrantTypes == null) {
            if (other.authorizedGrantTypes != null)
                return false;
        } else if (!authorizedGrantTypes.equals(other.authorizedGrantTypes))
            return false;
        if (clientId == null) {
            if (other.clientId != null)
                return false;
        } else if (!clientId.equals(other.clientId))
            return false;
        if (clientSecret == null) {
            if (other.clientSecret != null)
                return false;
        } else if (!clientSecret.equals(other.clientSecret))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (refreshTokenValidity == null) {
            if (other.refreshTokenValidity != null)
                return false;
        } else if (!refreshTokenValidity.equals(other.refreshTokenValidity))
            return false;
        if (resourceIds == null) {
            if (other.resourceIds != null)
                return false;
        } else if (!resourceIds.equals(other.resourceIds))
            return false;
        if (scope == null) {
            if (other.scope != null)
                return false;
        } else if (!scope.equals(other.scope))
            return false;
        if (webServerRedirectUri == null) {
            if (other.webServerRedirectUri != null)
                return false;
        } else if (!webServerRedirectUri.equals(other.webServerRedirectUri))
            return false;
        return true;
    }

}