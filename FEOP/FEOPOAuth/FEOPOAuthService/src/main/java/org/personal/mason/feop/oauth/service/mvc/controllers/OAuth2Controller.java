package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//@Controller
public class OAuth2Controller {
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    public void setConsumerTokenServices(ConsumerTokenServices consumerTokenServices) {
        this.consumerTokenServices = consumerTokenServices;
    }

    @RequestMapping("/oauth/users/{user}/tokens")
    public Collection<OAuth2AccessToken> listTokensForUser(@PathVariable String user, Principal principal) throws Exception {
        checkResourceOwner(user, principal);
        return enhance(consumerTokenServices.findTokensByUserName(user));
    }

    @RequestMapping(value = "/oauth/users/{user}/tokens/{token}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> revokeToken(@PathVariable String user, @PathVariable String token, Principal principal) throws Exception {
        checkResourceOwner(user, principal);
        if (consumerTokenServices.revokeToken(token)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/oauth/clients/{client}/tokens")
    public Collection<OAuth2AccessToken> listTokensForClient(@PathVariable String client) throws Exception {
        return enhance(consumerTokenServices.findTokensByClientId(client));
    }

    private Collection<OAuth2AccessToken> enhance(Collection<OAuth2AccessToken> tokens) {
        Collection<OAuth2AccessToken> result = new ArrayList<>();

        for (OAuth2AccessToken prototype : tokens) {
            DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(prototype);
            String clientId = consumerTokenServices.getClientId(token.getValue());
            if (clientId != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("client_id", clientId);
                token.setAdditionalInformation(map);
                result.add(token);
            }
        }
        return result;
    }

    private void checkResourceOwner(String user, Principal principal) {
        if (principal instanceof OAuth2Authentication) {
            OAuth2Authentication authentication = (OAuth2Authentication) principal;
            if (!authentication.isClientOnly() && !user.equals(principal.getName())) {
                throw new AccessDeniedException(String.format("User '%s' cannot obtain token for user '%s'.", principal.getName(), user));
            }
        }
    }
}
