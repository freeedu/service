package org.personal.mason.feop.oauth.contact.protocol;

public interface AccountInterface {

	AccountModel register(String oauthUser, String oauthSecret, String phoneNumber);

	boolean validate(String oauthuid, String token);

	boolean validateSecret(String oauthUid, String oauthSecret);

}
