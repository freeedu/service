package org.personal.mason.feop.oauth.service.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

public class ClientForm {
    @NotEmpty(message = "Application Name is required")
    private String clientName;
    @NotEmpty(message = "Application Type is required")
    private String clientType;
    @NotEmpty(message = "Redirect Url is required")
    @URL(message = "Invalid Url")
    private String redirectUrl;

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientType() {
        return clientType;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
}
