package org.personal.mason.feop.oauth.service.common.contact;

import org.personal.mason.feop.oauth.common.protocol.AccountInterface;
import org.personal.mason.feop.oauth.common.protocol.AccountModel;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepUser;
import org.personal.mason.feop.oauth.service.domain.service.common.FeopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountInfoProvider implements AccountInterface {

    private FeopUserService feopUserService;

    @Autowired
    public void setFeopUserService(FeopUserService feopUserService) {
        this.feopUserService = feopUserService;
    }

    @Override
    public AccountModel register(String oauthUser, String oauthSecret, String phoneNumber) {
        AccountModel model = new AccountModel();
        try {
            FoepUser user = new FoepUser();
            user.setEnabled(true);
            user.setPhone(phoneNumber);
            user.setEmail(oauthUser);
            user.setPassword(oauthSecret);
            feopUserService.regist(user);

            model.setAccountUid(user.getUserId());
            model.setSuccess(true);
            model.setMessage("Success");
        } catch (Exception e) {
            model.setSuccess(false);
            model.setMessage(e.getMessage());
        }
        return model;
    }

    @Override
    public AccountModel findAccount(String principleName) {
        AccountModel model = new AccountModel();
        FoepUser user = feopUserService.findByEmailOrUsername(principleName);
        if (user != null) {
            model.setAccountUid(user.getUserId());
            model.setSuccess(true);
            model.setMessage("Found");
        } else {
            model.setSuccess(false);
            model.setMessage("Not Found");
        }
        return model;
    }

    @Override
    public boolean validateSecret(String oauthUid, String oauthSecret) {
        FoepUser user = feopUserService.findByUserId(oauthUid);
        if (user != null) {
            return feopUserService.validate(oauthSecret, user);
        }
        return false;
    }

}
