package org.personal.mason.feop.oauth.service.mvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/10/13
 * Time: 11:44 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class TokenManageService {
    private ConsumerTokenServices consumerTokenServices;

    private TokenStore tokenStore;

    private UserApprovalHandler userApprovalHandler;

    @Autowired
    public void setConsumerTokenServices(ConsumerTokenServices consumerTokenServices){
        this.consumerTokenServices = consumerTokenServices;
    }

    @Autowired
    public void setTokenStore(TokenStore tokenStore){
        this.tokenStore = tokenStore;
    }

    @Autowired
    public void setUserApprovalHandler(UserApprovalHandler userApprovalHandler){
        this.userApprovalHandler = userApprovalHandler;
    }

    @RequestMapping("/oauth/users/tokens")
    @ResponseBody
    public Collection<OAuth2AccessToken> listTokens(@RequestParam String user, Principal principal){
        checkResourceOwner(user, principal);
        return tokenStore.findTokensByUserName(user);
    }

    @RequestMapping(value = {"/oauth/users/token"}, method = RequestMethod.DELETE)
    public ResponseEntity<Void> revokeToken(@RequestParam String user, @RequestParam String token, Principal principal){
        checkResourceOwner(user, principal);
        if(consumerTokenServices.revokeToken(token)){
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Used for get the client tokens
     *
     * @param client
     * @return
     */
    @RequestMapping("/oauth/client/tokens")
    @ResponseBody
    public Collection<OAuth2AccessToken> listTokensForClient(@RequestParam String client){
        return tokenStore.findTokensByClientId(client);
    }

    private void checkResourceOwner(String user, Principal principal) {
        if (principal instanceof OAuth2Authentication) {
            OAuth2Authentication authentication = (OAuth2Authentication) principal;
            if (!authentication.isClientOnly() && !user.equals(principal.getName())) {
                throw new AccessDeniedException(String.format("User '%s' cannot obtain tokens for user '%s'",
                        principal.getName(), user));
            }
        }
    }

}
