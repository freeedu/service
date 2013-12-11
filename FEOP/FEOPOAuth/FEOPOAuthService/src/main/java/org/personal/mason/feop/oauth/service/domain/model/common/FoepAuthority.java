package org.personal.mason.feop.oauth.service.domain.model.common;

import org.personal.mason.feop.oauth.common.domain.model.FOEPPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

/**
 * The persistent class for the roles database table.
 */
@Entity
@Table(name = "roles")
public class FoepAuthority extends FOEPPersistable<Long> {
    private static final long serialVersionUID = -1019367973729294374L;

    private Boolean enabled;

    private String name;

    // bi-directional many-to-many association to OUser
    @ManyToMany(mappedBy = "roles")
    private Set<FoepUser> users;

    @ManyToMany(mappedBy = "roles")
    private Set<FoepGroup> groups;

    public FoepAuthority() {
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

    public Set<FoepUser> getUsers() {
        return this.users;
    }

    public void setUsers(Set<FoepUser> users) {
        this.users = users;
    }

    public Set<FoepGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<FoepGroup> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FoepAuthority that = (FoepAuthority) o;

        if (!enabled.equals(that.enabled)) return false;
        if (!groups.equals(that.groups)) return false;
        if (!name.equals(that.name)) return false;
        if (!users.equals(that.users)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + enabled.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + users.hashCode();
        result = 31 * result + groups.hashCode();
        return result;
    }
}