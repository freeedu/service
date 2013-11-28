package org.personal.mason.feop.oauth.service.domain.model.oauth;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "password_reset")
public class PasswordReset extends AbstractPersistable<Long> {

    private static final long serialVersionUID = 3399070411158599314L;

    private String email;
    private String token;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "request_time")
    private Date requestTime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

}
