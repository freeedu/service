package org.personal.mason.feop.oauth.service.mvc.rest;

import org.personal.mason.feop.oauth.common.model.UserInfo;
import org.personal.mason.feop.oauth.common.model.UserRole;
import org.personal.mason.feop.oauth.service.common.oauth2.extention.FOEPTokenStore;
import org.personal.mason.feop.oauth.service.common.oauth2.extention.FOEPUserDetailsService;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepUser;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountService {

    private FeopAccessTokenService feopAccessTokenService;
    private FOEPUserDetailsService foepUserDetailsService;
    private FOEPTokenStore foepTokenStore;

    @Autowired
    public void setFeopAccessTokenService(FeopAccessTokenService feopAccessTokenService) {
        this.feopAccessTokenService = feopAccessTokenService;
    }

    @Autowired
    public void setFoepUserDetailsService(FOEPUserDetailsService foepUserDetailsService) {
        this.foepUserDetailsService = foepUserDetailsService;
    }

    @Autowired
    public void setFoepTokenStore(FOEPTokenStore foepTokenStore) {
        this.foepTokenStore = foepTokenStore;
    }

    @RequestMapping(value = {"/userinfo"}, method = RequestMethod.GET)
    @ResponseBody
    public UserInfo retrieveUserInfo(@RequestParam("token") String token) {

        OAuth2AccessToken auth2AccessToken = foepTokenStore.readAccessToken(token);

        OAuth2Authentication authentication = foepTokenStore.readAuthentication(token);

        if (auth2AccessToken == null || auth2AccessToken.isExpired() || authentication == null) {
            return null;
        }


        String userName = authentication.isClientOnly() ? null : authentication.getName();

        if (userName == null) {
            return null;
        }

        FoepUser user = foepUserDetailsService.findByEmailOrUsernameOrPhone(userName);
        if (user == null) {
            return null;
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getUserId());
        if (user.getUserName() != null) {
            userInfo.setScreenName(user.getUserName());
        } else {
            userInfo.setScreenName(user.getEmail());
        }
        userInfo.setEmail(userName);
        userInfo.setPhone(user.getPhone());

        if (user.getRoles() != null) {
            for (FoepAuthority role : user.getRoles()) {
                if (role.getEnabled().booleanValue()) {
                    UserRole ur = new UserRole();
                    ur.setRoleName(role.getName());
                    userInfo.getRoles().add(ur);
                }
            }
        }
        return userInfo;
    }

}
