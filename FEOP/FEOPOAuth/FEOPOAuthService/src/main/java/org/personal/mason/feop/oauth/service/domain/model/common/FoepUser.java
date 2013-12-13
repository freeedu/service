package org.personal.mason.feop.oauth.service.domain.model.common;

import org.personal.mason.feop.oauth.common.domain.model.FOEPPersistable;

import javax.persistence.*;
import java.util.Set;

/**
 * The persistent class for the users database table.
 */
@Entity
@Table(name = "users")
public class FoepUser extends FOEPPersistable<Long> {
    private static final long serialVersionUID = 4887499676134613261L;

    @Column(name = "user_email", unique = true)
    private String email;
    @Column(name = "user_phone", unique = true)
    private String phone;
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "enabled")
    private Boolean enabled = true;
    @Column(name = "account_non_expired")
    private Boolean accountNonExpired = true;
    @Column(name = "cred_non_expired")
    private Boolean credentialsNonExpired = true;
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked = true;

    // bi-directional many-to-many association to ORole
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<FoepAuthority> roles;

    @ManyToMany(mappedBy = "users")
    private Set<FoepGroup> groups;

    public FoepUser() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<FoepAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Set<FoepAuthority> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Set<FoepGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<FoepGroup> groups) {
        this.groups = groups;
    }

}