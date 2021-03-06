package org.personal.mason.feop.oauth.service.domain.model.common;

import org.personal.mason.feop.oauth.common.domain.model.FOEPPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "invite_code")
public class InvitingCode extends FOEPPersistable<Long> {

    private static final long serialVersionUID = -4404481291665733440L;

    @Column(name = "invite_code", unique = true)
    private String inviteCode;
    private Boolean used = false;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }
}
