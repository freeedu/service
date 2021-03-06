package org.personal.mason.feop.oauth.service.domain.model.common;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.personal.mason.feop.oauth.common.domain.model.FOEPPersistable;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/11/13
 * Time: 11:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "groups")
public class FoepGroup extends FOEPPersistable<Long>{
	private static final long serialVersionUID = 1027008699525310957L;

	@Column(name = "group_name", length = 64, nullable = false)
    private String groupName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_roles", joinColumns = {@JoinColumn(name = "group_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<FoepAuthority> roles;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = FoepUser.class)
    @JoinTable(name = "group_members", joinColumns = {@JoinColumn(name = "group_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<FoepUser> users;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<FoepAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Set<FoepAuthority> roles) {
        this.roles = roles;
    }

    public Set<FoepUser> getUsers() {
        return users;
    }

    public void setUsers(Set<FoepUser> users) {
        this.users = users;
    }
}
