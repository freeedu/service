package org.personal.mason.feop.oauth.service.domain.model.oauth;

import org.personal.mason.feop.oauth.common.domain.model.FOEPPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * The persistent class for the roles database table.
 */
@Entity
@Table(name = "roles")
public class OauthRole extends FOEPPersistable<Long> {
    private static final long serialVersionUID = -1019367973729294374L;

    private Boolean enabled;

    private String name;

    // bi-directional many-to-many association to OUser
    @ManyToMany(mappedBy = "roles")
    private List<OauthUser> users;

    public OauthRole() {
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OauthUser> getUsers() {
        return this.users;
    }

    public void setUsers(List<OauthUser> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((users == null) ? 0 : users.hashCode());
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
        OauthRole other = (OauthRole) obj;
        if (enabled == null) {
            if (other.enabled != null)
                return false;
        } else if (!enabled.equals(other.enabled))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (users == null) {
            if (other.users != null)
                return false;
        } else if (!users.equals(other.users))
            return false;
        return true;
    }

}