package org.personal.mason.feop.oauth.service.common.oauth2;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepUser;
import org.personal.mason.feop.oauth.service.domain.service.common.FeopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class OAuth2UserDetailsServiceImpl implements UserDetailsService {

    private FeopUserService feopUserService;

    @Autowired
    public void setFeopUserService(FeopUserService feopUserService) {
        this.feopUserService = feopUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        FoepUser user = feopUserService.findByEmailOrUsername(username);
        System.out.println("UserDetails:" + user);

        if (null == user) {
            throw new UsernameNotFoundException("Invalid User");
        }

        Collection<GrantedAuthority> grantedAuths = this.obtionGrantedAuthorities(user);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        User userDetail = new User(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);

        return userDetail;
    }

    private Set<GrantedAuthority> obtionGrantedAuthorities(FoepUser user) {

        if (null == user) {
            return null;
        }

        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();

        List<String> roles = feopUserService.findUserRoles(user);

        for (String role : roles) {
            authSet.add(new SimpleGrantedAuthority(role));
        }

        return authSet;
    }

}
