package org.personal.mason.feop.oauth.service.common.oauth2.extention;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/13/13
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class FoepBasicUser implements FoepUserDetails {
	private static final long serialVersionUID = -7900899062089231950L;
	private Long id;
    private String email;
    private String phone;
    private String password;
    private final String username;
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    /**
     *
     * @param id
     * @param email
     * @param phone
     * @param authorities
     * @param password
     * @param username
     * @param accountNonExpired
     * @param accountNonLocked
     * @param credentialsNonExpired
     * @param enabled
     */
    public FoepBasicUser(Long id, String email, String phone, Set<GrantedAuthority> authorities, String password, String username, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.authorities = authorities;
        this.password = password;
        this.username = username;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUserEmail() {
        return this.email;
    }

    @Override
    public String getUserPhone() {
        return this.phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
