package org.personal.mason.feop.oauth.contact.protocol;

public class AccountModel {
private String accountUid;
private boolean success;
private String message;

public String getAccountUid() {
	return accountUid;
}

public void setAccountUid(String accountUid) {
	this.accountUid = accountUid;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public boolean isSuccess() {
	return success;
}

public void setSuccess(boolean success) {
	this.success = success;
}

}
