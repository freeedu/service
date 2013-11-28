package org.personal.mason.feop.oauth.common.protocol;

public interface AccountInterface {

    AccountModel register(String oauthUser, String oauthSecret, String phoneNumber);

    AccountModel findAccount(String principle);

    boolean validateSecret(String oauthUid, String oauthSecret);

}
