package org.personal.mason.feop.oauth.service.mvc.rest;

import org.personal.mason.feop.oauth.common.model.UserInfo;
import org.personal.mason.feop.oauth.common.model.UserRole;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepUser;
import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthAccessToken;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopAccessTokenService;
import org.personal.mason.feop.oauth.service.domain.service.common.FeopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountService {

    private FeopAccessTokenService feopAccessTokenService;
    private FeopUserService feopUserService;

    @Autowired
    public void setFeopAccessTokenService(FeopAccessTokenService feopAccessTokenService) {
        this.feopAccessTokenService = feopAccessTokenService;
    }

    @Autowired
    public void setFeopUserService(FeopUserService feopUserService) {
        this.feopUserService = feopUserService;
    }

    @RequestMapping(value = {"/userinfo"}, method = RequestMethod.GET)
    @ResponseBody
    public UserInfo retrieveUserInfo(@RequestParam("token") String token) {

        OauthAccessToken accessToken = feopAccessTokenService.findAccessTokenWithTokenId(token);
        if (accessToken != null) {
            String userName = accessToken.getUserName();

            FoepUser user = feopUserService.findByEmailOrUsername(userName);
            if (user != null) {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(user.getUserId());
                userInfo.setFirstName(user.getFirstName());
                userInfo.setLastName(user.getLastName());
                if (user.getUserName() != null) {
                    userInfo.setScreenName(user.getUserName());
                } else {
                    userInfo.setScreenName(user.getEmail());
                }
                userInfo.setGender(user.getGender());
                userInfo.setBirth(user.getBirth());
                userInfo.setProfileImageUri(user.getProfileImageUri());
                userInfo.setEmail(userName);
                userInfo.setPhone(user.getPhone());
                userInfo.setLocation(user.getLocation());

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

        return null;
    }

}
