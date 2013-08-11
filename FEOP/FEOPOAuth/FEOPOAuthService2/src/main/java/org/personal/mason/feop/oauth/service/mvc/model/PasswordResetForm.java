package org.personal.mason.feop.oauth.service.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

public class PasswordResetForm {
	@NotEmpty(message = "Invalid token")
	private String token;

	@NotEmpty(message = "New Password is required")
	private String newPassword;

	@NotEmpty(message = "Repeat Password is required")
	private String repeatPassword;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

}
