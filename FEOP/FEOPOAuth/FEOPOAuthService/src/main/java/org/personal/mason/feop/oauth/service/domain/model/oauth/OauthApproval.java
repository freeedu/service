package org.personal.mason.feop.oauth.service.domain.model.oauth;

import org.personal.mason.feop.oauth.common.domain.model.FOEPPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/11/13
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "oauth_approvals")
public class OauthApproval extends FOEPPersistable<Long> {
	private static final long serialVersionUID = -2237619438295617875L;
	@Column(name = "user_id")
    private String user;
    @Column(name = "client_id")
    private String client;
    @Column(name = "scoper")
    private String scope;
    @Column(name = "status")
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expires_at")
    private Date expiresAt;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}
