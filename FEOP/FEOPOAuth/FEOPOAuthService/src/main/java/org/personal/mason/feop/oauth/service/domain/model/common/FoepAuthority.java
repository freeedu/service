package org.personal.mason.feop.oauth.service.domain.model.common;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.personal.mason.feop.oauth.common.domain.model.FOEPPersistable;

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

}