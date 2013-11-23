package org.personal.mason.feop.oauth.contact.common;

import org.personal.mason.feop.oauth.common.protocol.AccountInterface;
import org.personal.mason.feop.oauth.common.protocol.AccountModel;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 11/23/13
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class DefaultContactApiImpl implements AccountInterface {


    @Override
    public AccountModel register(String oauthUser, String oauthSecret, String phoneNumber) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccountModel findAccount(String principle) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean validateSecret(String oauthUid, String oauthSecret) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
