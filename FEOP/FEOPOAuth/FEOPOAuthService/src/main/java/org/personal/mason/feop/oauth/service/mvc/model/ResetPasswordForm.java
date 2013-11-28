package org.personal.mason.feop.oauth.service.mvc.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ResetPasswordForm {

    @Email(message = "Please provide a valid email address")
    @NotEmpty(message = "Email is required")
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
