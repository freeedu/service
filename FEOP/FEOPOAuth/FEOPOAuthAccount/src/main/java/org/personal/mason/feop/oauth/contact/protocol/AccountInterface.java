package org.personal.mason.feop.oauth.contact.protocol;

public interface AccountInterface {

	AccountModel register(String oauthUser, String oauthSecret, String phoneNumber);

	AccountModel findAccount(String principle);

	boolean validateSecret(String oauthUid, String oauthSecret);

}
