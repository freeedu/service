package org.personal.mason.feop.oauth.service.mvc.autologin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AutologinAuthenticationManager implements AuthenticationManager {

	private AutoLoginCodeStore codeStore;

	@Autowired
	public void setCodeStore(AutoLoginCodeStore codeStore) {
		if (codeStore == null) {
			throw new IllegalArgumentException("code store cannot be null");
		}
		this.codeStore = codeStore;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!(authentication instanceof AutoLoginAuthenticationRequest)) {
			return null;
		}

		AutoLoginAuthenticationRequest request = (AutoLoginAuthenticationRequest) authentication;
		Map<String, String> info = request.getInfo();
		String code = info.get("code");

		Authentication user = codeStore.getUser(code);
		if (user == null) {
			throw new BadCredentialsException("Cannot redeem provided code for user");
		}
		
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		result.setDetails(authentication.getDetails());
		return result;
	}

}
