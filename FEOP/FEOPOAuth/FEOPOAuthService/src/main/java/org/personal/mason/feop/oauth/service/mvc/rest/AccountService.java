package org.personal.mason.feop.oauth.service.mvc.rest;

import org.personal.mason.feop.oauth.common.model.UserInfo;
import org.personal.mason.feop.oauth.common.model.UserRole;
import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthRole;
import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthUser;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class AccountService {

    private FeopUserService feopUserService;

    @Autowired
    public void setFeopUserService(FeopUserService feopUserService) {
        this.feopUserService = feopUserService;
    }

    @RequestMapping(value = {"/userinfo"}, method = RequestMethod.GET)
    @ResponseBody
    public UserInfo retrieveUserInfo(Principal principal) {
        String email = principal.getName();
        OauthUser user = feopUserService.findByEmailOrUsername(email);
        if (user != null) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(user.getUserId());
            userInfo.setFirstName(user.getFirstName());
            userInfo.setLastName(user.getLastName());
            userInfo.setScreenName(user.getUserName());
            userInfo.setGender(user.getGender());
            userInfo.setBirth(user.getBirth());
            userInfo.setProfileImageUri(user.getProfileImageUri());
            userInfo.setEmail(email);
            userInfo.setPhone(user.getPhone());
            userInfo.setLocation(user.getLocation());

            if (user.getRoles() != null) {
                for (OauthRole role : user.getRoles()) {
                    if (role.getEnabled().booleanValue()) {
                        UserRole ur = new UserRole();
                        ur.setRoleName(role.getName());
                        userInfo.getRoles().add(ur);
                    }
                }
            }
            return userInfo;
        }

        return null;
    }

}
