package org.personal.mason.feop.oauth.service.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangePasswordForm {
    @NotEmpty(message = "Old Password is required")
    private String oldPassword;

    @NotEmpty(message = "New Password is required")
    private String newPassword;

    @NotEmpty(message = "Repeat Password is required")
    private String repeatPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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
