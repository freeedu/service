package org.personal.mason.feop.oauth.service.mvc.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class SignupForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;
	@NotEmpty(message = "Last Name is required")
	private String lastName;
	private String inviteCode;
	@Email(message = "Please provide a valid email address")
	@NotEmpty(message = "Email is required")
	private String email;
	@NotEmpty(message = "Password is required")
	private String password;
	@NotEmpty(message = "Repeat Password is required")
	private String repeatPassword;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}
}
