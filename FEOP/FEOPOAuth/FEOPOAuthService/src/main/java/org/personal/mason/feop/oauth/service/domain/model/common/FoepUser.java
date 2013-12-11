package org.personal.mason.feop.oauth.service.domain.model.common;

import org.personal.mason.feop.oauth.common.domain.model.FOEPPersistable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The persistent class for the users database table.
 */
@Entity
@Table(name = "users")
public class FoepUser extends FOEPPersistable<Long> {
    private static final long serialVersionUID = 4887499676134613261L;

    @Column(name = "fname")
    private String firstName;
    @Column(name = "lname")
    private String lastName;

    private String gender;

    @Temporal(TemporalType.DATE)
    private Date birth;
    @Column(name = "profile_img_uri")
    private String profileImageUri;
    private String location;

    private String email;

    private String password;

    private String phone;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;
    @Column(name = "cred_non_expired")
    private Boolean credentialsNonExpired;
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    // bi-directional many-to-many association to ORole
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<FoepAuthority> roles;

    @ManyToMany(mappedBy = "users")
    private Set<FoepGroup> groups;

    public FoepUser() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getProfileImageUri() {
        return profileImageUri;
    }

    public void setProfileImageUri(String profileImageUri) {
        this.profileImageUri = profileImageUri;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoepUser)) return false;
        if (!super.equals(o)) return false;

        FoepUser foepUser = (FoepUser) o;

        if (accountNonExpired != null ? !accountNonExpired.equals(foepUser.accountNonExpired) : foepUser.accountNonExpired != null)
            return false;
        if (accountNonLocked != null ? !accountNonLocked.equals(foepUser.accountNonLocked) : foepUser.accountNonLocked != null)
            return false;
        if (birth != null ? !birth.equals(foepUser.birth) : foepUser.birth != null) return false;
        if (credentialsNonExpired != null ? !credentialsNonExpired.equals(foepUser.credentialsNonExpired) : foepUser.credentialsNonExpired != null)
            return false;
        if (email != null ? !email.equals(foepUser.email) : foepUser.email != null) return false;
        if (enabled != null ? !enabled.equals(foepUser.enabled) : foepUser.enabled != null) return false;
        if (firstName != null ? !firstName.equals(foepUser.firstName) : foepUser.firstName != null) return false;
        if (gender != null ? !gender.equals(foepUser.gender) : foepUser.gender != null) return false;
        if (groups != null ? !groups.equals(foepUser.groups) : foepUser.groups != null) return false;
        if (lastName != null ? !lastName.equals(foepUser.lastName) : foepUser.lastName != null) return false;
        if (location != null ? !location.equals(foepUser.location) : foepUser.location != null) return false;
        if (password != null ? !password.equals(foepUser.password) : foepUser.password != null) return false;
        if (phone != null ? !phone.equals(foepUser.phone) : foepUser.phone != null) return false;
        if (profileImageUri != null ? !profileImageUri.equals(foepUser.profileImageUri) : foepUser.profileImageUri != null)
            return false;
        if (roles != null ? !roles.equals(foepUser.roles) : foepUser.roles != null) return false;
        if (userId != null ? !userId.equals(foepUser.userId) : foepUser.userId != null) return false;
        if (userName != null ? !userName.equals(foepUser.userName) : foepUser.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        result = 31 * result + (profileImageUri != null ? profileImageUri.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (accountNonExpired != null ? accountNonExpired.hashCode() : 0);
        result = 31 * result + (credentialsNonExpired != null ? credentialsNonExpired.hashCode() : 0);
        result = 31 * result + (accountNonLocked != null ? accountNonLocked.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }
}