package org.personal.mason.feop.oauth.contact.domain.model;

import org.personal.mason.feop.oauth.common.domain.model.FOEPPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the contact_remind_date database table.
 */
@Entity
@Table(name = "contact_remind_date")
public class ContactRemindDate extends FOEPPersistable<Long> {

    private static final long serialVersionUID = -7014293686255922337L;

    @Column(length = 60)
    private String label;

    @Column(nullable = false)
    private Boolean remind;

    @Temporal(TemporalType.DATE)
    @Column(name = "remind_date", nullable = false)
    private Date remindDate;

    // bi-directional many-to-one association to Contact
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    public ContactRemindDate() {
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getRemind() {
        return this.remind;
    }

    public void setRemind(Boolean remind) {
        this.remind = remind;
    }

    public Date getRemindDate() {
        return this.remindDate;
    }

    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}