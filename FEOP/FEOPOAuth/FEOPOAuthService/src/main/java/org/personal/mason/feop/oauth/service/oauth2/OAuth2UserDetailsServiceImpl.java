package org.personal.mason.feop.oauth.service.oauth2;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.personal.mason.feop.oauth.service.domain.OauthUser;
import org.personal.mason.feop.oauth.service.spi.OUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class OAuth2UserDetailsServiceImpl implements UserDetailsService {

	private OUserService userService;

	@Autowired
	public void setUserService(OUserService userService) {
		if (userService == null) {
			throw new IllegalArgumentException("user Service cannot be null");
		}
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		OauthUser user = userService.findByEmailOrUsername(username);
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

	private Set<GrantedAuthority> obtionGrantedAuthorities(OauthUser user) {

		if (null == user) {
			return null;
		}

		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();

		List<String> roles = userService.findUserRoles(user);

		for (String role : roles) {
			authSet.add(new SimpleGrantedAuthority(role));
		}

		return authSet;
	}

}
